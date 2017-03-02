
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<table class="table table-bordered">
				<tr  style="background: #2f87c1;color: #edfbfb;">
					<th colspan="6">车主信息</th>
				</tr>
				<tr>
					<th>ID</th>
					<th>微信昵称</th>
					<th>手机号</th>
					<th>维护人员</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
				<tbody id="owner_table_tr"></tbody>
			</table>

		</div>
	</div>
</div>


<script >
    $(function(){
		findOwner();
	});
	function findOwner() {//查询车主信息
		$.ajax({
			type: "GET",
			url: "${basePath}/vehiclemanage/findOwner.shtml",
			data: "vhclId="+$("#vhclId").val(),
			dataType: "json",
			success: function (code) {
			   if(code.result == 1) {
				   var user = code.data.user;
				   var vehicle = code.data;
				   if(user!=null){
//							   if (vehicle.updateUser != null && vehicle.updateUser >0) {
					   var time = getDate(vehicle.publicDate, "yyyy-MM-dd hh:mm:ss")
//							   }
					   var table_th_maintab = '<tr><td><input type="hidden" id="userId" value="' + user.id + '"/>' + user.id + '</td>' +
							   '<td>' + user.nickName + '</td>' +
							   '<td><div id="editDivPhone">' + vehicle.telephone + '</div></td>' +
							   '<td>' + vehicle.updateUserName + '</td>' +
							   '<td>' +  time + '</td>' +
							   '<td><a href="javascript:edit();" id="ownerEditClick">编辑</a></td></tr>';
					   $("#owner_table_tr").html(table_th_maintab);
					}else{
					   $("#owner_table_tr").html('<tr><td colspan="6">暂无数据！</td></tr>');
				   }
				}else{
					alert("车主信息查询失败-_-!");
				}
			}
		});
	}

    function edit(){ //点击编辑
		var text=$("#editDivPhone");
		var val=text.text();
		text.html("<input type='text' id='editTelephone' value="+val+" />");
		$("#editTelephone").blur(function(){
			var telephone = $("#editTelephone").val()
			if(isMobilePhone(telephone)) {
                if (confirm("是否保存？")) {
                    text.html($("#editTelephone").val());
                    $.ajax({
                        type: "GET",
                        url: "${basePath}/vehiclemanage/saveOwner.shtml",
                        data: "vhclId=" + $("#vhclId").val() + "&telephone=" + telephone,
                        dataType: "json",
                        success: function (code) {
                            alert(code.data);
                            findOwner();
                        }
                    });
                } else {
                    text.html(val);
                }
            }else{
				alert("请输入正确的手机号码！");
			}
		});
    }

</script>