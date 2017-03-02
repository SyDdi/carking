
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<form id="carquote_form">
				<table class="table table-bordered">
                    <tr  style="background: #2f87c1;color: #edfbfb;">
                        <th>推送消息</th>
                    </tr>
					<tr>
						<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title：<input type="text" id="title" style="width: 300px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;内     &nbsp;&nbsp;容：<textarea rows="3" cols="80" id="content"></textarea></td>
					</tr>
                    <tr>
                        <td>remark：<input type="text" id="remark"></input></td>
                    </tr>
                    <tr><td><a href="#" onclick="pushMsg();">推送</a></td></tr>
				</table>
            </form>
		</div>
	</div>
</div>


<script >

    /**
     * 推送消息
     */
    function pushMsg(){
        if(confirm("确定推送消息给车主？")) {
            var vhclId = $("#vhclId").val();
            var content = $("#content").val();
            var title = $("#title").val();
            var remark = $("#remark").val();
            var param ="vhclId="+vhclId+"&content="+content+"&title="+title+"&remark="+remark;
            console.info(param);
            $.ajax({
                type: "GET",
                url: "${basePath}/vehiclemanage/pushMsg.shtml",
                data: param,
                dataType: "json",
                success: function (code) {
                    alert(code.data);
                }
            });
        }
    }

</script>