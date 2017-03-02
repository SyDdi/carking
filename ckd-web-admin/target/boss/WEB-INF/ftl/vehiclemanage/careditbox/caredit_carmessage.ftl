
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<form id="carquote_form">
				<table class="table table-bordered">
					<tr  style="background: #2f87c1;color: #edfbfb;">
						<th colspan="5">留言</th>
					</tr>
					<tr>
						<th>ID</th>
						<th>微信昵称</th>
						<th>留言</th>
						<th>留言时间</th>
						<th>操作</th>
					</tr>

                    <tbody id="carMessage_tr">

                    </tbody>
				</table>
            </form>
		</div>
	</div>
</div>


<script >

    $(function(){
        findcarMessage();
    });

    function findcarMessage(){
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/findcarMessage.shtml",
            data: "vhclId="+ $('#vhclId').val(),
            dataType: "json",
            success: function (code) {
                if(code.data){
                    var data = code.data;
                    var trHtml = "<tr ><td colspan=\"5\">暂无数据</td>";
                    if(data != ""  && data != null) {
                        trHtml ="";
                        $.each(data, function (i, item) {//http://localhost:8080
                            var time = item.createDate;
                            if (time == null) {
                                time = "--";
                            } else {
                                time = getDate(time, "yyyy-MM-dd hh:mm:ss");
                            }
                            var userId = $("#userId").val();
                            var color = "";
                            if(userId == item.userId){
                              color = 'style="color:blue"'
                            }
                            trHtml += '<tr id="messageTr_' + item.id + '">' +
                                    '<td>' + item.userId + '</td>' +
                                    '<td '+color+' >' + (item.userName == null ? "-" :item.userName) + '</td>' +
                                    '<td>' + item.content + '</td>' +
                                    '<td>' + time + '</td>' +
                                    '<td><a id="carquote_edit' + item.id + '" onclick="carMessage_Del(' + item.id + ');">删除</a></td></tr>';
                        })
                    }
                    $("#carMessage_tr").html(trHtml);
                }

            }
        });
    }

    /**
     * 删除留言
     * @param id
     */
    function carMessage_Del(id){
        if(confirm("是否删除留言！")) {
            var param ="id="+id;
            $.ajax({
                type: "GET",
                url: "${basePath}/vehiclemanage/carMessageDel.shtml",
                data: param,
                dataType: "json",
                success: function (code) {
                    alert(code.data);
                    if(code.result == 1) {
                        $("#messageTr_"+id).remove();
                    }
                }
            });
        }
    }

</script>