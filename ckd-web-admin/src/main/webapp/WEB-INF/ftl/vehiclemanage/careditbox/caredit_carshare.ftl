
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<table class="table table-bordered">
				<tr >
					<th colspan="6" style="background: #2f87c1;color: #edfbfb;">车主分享信息</th>
					<th><a id="carShare_edit" onclick="carShare_edit(this);">编辑</a></th>
				</tr>
				<tr>
					<th>车辆ID</th>
					<th>车源生成时间</th>
					<th>车源更新时间</th>
					<th>卖家直接分享次数</th>
					<th>间接分享次数</th>
					<th>收藏该车人数</th>
					<th>是否删除车源</th>
				</tr>
				<tbody id="carShare_table_tr"></tbody>
			</table>

		</div>
	</div>
</div>


<script >
	$(function(){
        findCarShare();
	})

  function findCarShare() {//查询车主信息
	$.ajax({
		type: "GET",
		url: "${basePath}/vehiclemanage/findVehicle.shtml",
		data: "vhclId="+$("#vhclId").val(),
		dataType: "json",
		success: function (code) {
		   if(code.result == 1){
			   var vhcl = code.data;
			   var time = vhcl.publicDate;
			   if(time != null && time !=""){
					time = getDate(time,"yyyy-MM-dd hh:mm:ss");
			   }else{
				   time = "-";
			   }
			   var table_th_maintab = '<tr><td><a href="${weixinURL}/car/'+code.HashvhclId+'.html" target="_blank">' +vhcl.id +'</a></td>' +
					   '<td>' +getDate(vhcl.createDate,"yyyy-MM-dd hh:mm:ss")+ '</td>' +
					   '<td>'+ time +'</td>' +
					   '<td>'+code.direct+'</td>' +
					   '<td>'+code.indirect+'</td>' +
					   '<td>'+code.shareCount+'</td>' +
					   '<td><select id="carShare_updStatus" onchange="carShare_updEdit(this);" disabled>'+
					   '<option value="0"'+(vhcl.isDelete == 0 ? "selected" :"")+'>否</option>'+
					   '<option value="1"'+(vhcl.isDelete == 1 ? "selected" :"")+'>是</option>'+
					   '</select></tr>';
				$("#carShare_table_tr").html(table_th_maintab);
			}else{
				alert("车辆分享信息查询失败-_-!");
			}
		}
	});
}

    /**
     * 隐藏编辑 显示是否选项
	 * @param opt
     */
	function carShare_edit(opt){
		$("#carShare_updStatus").attr("disabled",false);
	}
    /**
     * 保存修改
 	 * @param opt
     */
    function carShare_updEdit(opt){
        var isDelete =  $(opt).val();
		if(confirm("是否保存数据！")){
            $.ajax({
                type: "GET",
                url: "${basePath}/vehiclemanage/saveShare.shtml",
                data: "vhclId=" + $("#vhclId").val() + "&isDelete=" + isDelete,
                dataType: "json",
                success: function (code) {
                    alert(code.data);
                    findCarShare();
                }
            });
		}else{
			//选择取消的时候 恢复当前车辆状态
            if(status == 0){
                $("#carShare_updStatus").val(1);
            }else{
                $("#carShare_updStatus").val(0);
            }
            $(opt).attr("disabled","disabled");
		}
    }

</script>