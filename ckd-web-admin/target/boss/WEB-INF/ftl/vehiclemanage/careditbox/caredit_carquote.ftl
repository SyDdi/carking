
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<form id="carquote_form">
				<table class="table table-bordered">
					<tr >
						<th colspan="5" style="background: #2f87c1;color: #edfbfb;">车商报价</th>
                        <th><a id="carquote_edit' + item.id + '" onclick="$('#addQuote').modal();findAddQuote();">增加车商报价</a></th>
					</tr>
					<tr>
						<th>车商</th>
						<th>报价（万元）</th>
						<th>维护人员</th>
						<th>更新时间</th>
						<th>车主是否满意报价</th>
						<th>操作</th>
					</tr>

                    <tbody id="carquote_tr">

                    </tbody>
				</table>
            </form>
		</div>
	</div>
</div>
<div class="modal fade" id="addQuote" tabindex="-1" role="dialog" aria-labelledby="addQuoteLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addQuoteLabel">添加车商报价</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">

                    <table class="table table-bordered">
                        <tr>
                            <th>车商</th>
                            <th>报价（万元）</th>
                            <th>车主是否满意报价</th>
                            <th>操作</th>
                        </tr>
                         <tbody id="addQuote_body"></tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <#--<button type="button" onclick="addQuote();" class="btn btn-primary">Save</button>-->
            </div>
        </div>
    </div>
</div>

<script >

    $(function(){
        findCarQuote();
    });
    //点击编辑
    function carquote_edit(id){
        $("#carquote_editDiv"+id).show();
        $("#carquote_edit"+id).hide();
        $(':input','#tr_'+id).not(':button, :submit, :reset, :hidden').removeAttr('disabled');
    }
    //点击取消
    function carQuoteCancel(id){
        $("#carquote_editDiv"+id).hide();
        $("#carquote_edit"+id).show();
        $(':input','#tr_'+id).not(':button, :submit, :reset, :hidden').attr('disabled','disabled');
    }

    /**
     * 初始化弹窗车商列表
     */
    function findAddQuote(){
	        $.ajax({
	            type: "GET",
	            url: "${basePath}/vehiclemanage/findAddQuote.shtml?vhclId="+ $('#vhclId').val(),
	            dataType: "json",
	            success: function (code) {
	                if(code.data) {
	                    var data = code.data;
	                    var trHtml = "<tr ><td colspan=\"6\">暂无商家</td></tr>";
	                    if (data != "" && data != null) {
	                        trHtml = ""
	                        $.each(data, function (i, item) {//http://localhost:8080
	                            trHtml += '<tr id="tr_' + item.id + '">' +
	                                    '<td>' + item.nickName + '</td>' +
	                                    '<td><input type="text" id="addQuote' + item.id + '" name="quote" onkeyup="clearNoNum(this);"></td>' +
	                                    '<td><select id="addQuote_status' + item.id + '" name="status">' +
	                                    '<option value="-1" >未选择</option>' +
	                                    '<option value="0">不满意</option>' +
	                                    '<option value="1">满意</option>' +
	                                    '</select></td>' +
	                                    '<td><a id="carquote_edit' + item.id + '" onclick="addQquote(' + item.id + ');">添加</a></td></tr>';
	                        });
	                    }
	                    $("#addQuote_body").html(trHtml);
	                    
	                }
	
	            }
	        });
    }

    /**
     * 初始化报价列表
     */
    function findCarQuote(){
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/findCarQuote.shtml",
            data: "vhclId="+ $('#vhclId').val(),
            dataType: "json",
            success: function (code) {
                if(code.data) {
                    var data = code.data;

                    var trHtml = "<tr ><td colspan=\"6\">暂无数据</td></tr>";
                    if (data != "" && data != null) {
                        trHtml = ""
                        $.each(data, function (i, item) {//http://localhost:8080
                            var time = item.updateDate;
                            if (time == null) {
                                time = "--";
                            } else {
                                time = getDate(time, "yyyy-MM-dd hh:mm:ss");
                            }
                            trHtml += '<tr id="tr_' + item.id + '">' +
                                    '<td>' + item.dealerName + '</td>' +
                                    '<td><input type="text" id="quote' + item.id + '" name="quote" value="' + (item.quote / 10000.00) + '"  onkeyup="clearNoNum(this);setStatus(this,' + item.id + ');" disabled></td>' +
                                    '<td>' + item.updateUserName + '</td>' +
                                    '<td>' + time + '</td>' +
                                    '<td><select id="carQuote_status' + item.id + '" name="status" disabled>' +
                                    '<option value="-1" '+(item.status == -1 ? "selected" :"")+'>未选择</option>' +
                                    '<option value="0" '+(item.status == 0 ? "selected" :"")+'>不满意</option>' +
                                    '<option value="1" '+ (item.status == 1 ? "selected" :"")+'>满意</option>' +
                                    '</select></td>' +
                                    '<td><div id="carquote_editDiv' + item.id + '" style="display: none;">' +
                                    '<a onclick="carQuote_save(' + item.id + ');">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;' +
                                    '<a onclick="carQuote_delete(' + item.id + ');">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;' +
                                    '<a onclick="carQuoteCancel(' + item.id + ');">取消</a>' +
                                    '</div><a id="carquote_edit' + item.id + '" onclick="carquote_edit(' + item.id + ');">编辑</a></td></tr>';
                        });
                    }
                    $("#carquote_tr").html(trHtml);
                }
            }
        });
    }
    //输入报价是改变车主选中状态
    function setStatus(opt,quote){
        if(opt.value == ""){
            $("#carQuote_status"+quote).attr("disabled","disabled").val(-1);
        }else{
            $("#carQuote_status"+quote).attr("disabled",false).val(-1);
        }
    }
    //保存报价
    function carQuote_save(id){
        var quote = $('#quote'+id).val();
        var carQuoteStatus = $("#carQuote_status"+id).val();
        var param ="bidPrice="+quote+"&status="+carQuoteStatus+"&id="+id;
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/updCarQuote.shtml",
            data: param,
            dataType: "json",
            success: function (code) {
                alert(code.data );
                if(code.result == 1){
                    carQuoteCancel(id);
                    findCarQuote();
                }
            }
        });
    }
    //删除报价
    function carQuote_delete(id){
        var param ="id="+id;
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/delCarQuote.shtml",
            data: param,
            dataType: "json",
            success: function (code) {
                alert(code.data );
                if(code.result == 1){
                    carQuoteCancel(id);
                    findCarQuote();
                }
            }
        });
    }

    function addQquote(dealerId){
        var quote = $('#addQuote'+dealerId).val();
        var carQuoteStatus = $("#addQuote_status"+dealerId).val();
        var vhclId = $('#vhclId').val();;
        var param ="bidPrice="+quote+"&status="+carQuoteStatus+"&dealerId="+dealerId+"&vehicleId="+vhclId;
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/addQquote.shtml",
            data: param,
            dataType: "json",
            success: function (code) {
                alert(code.data );
                if(code.result == 1){
                    findCarQuote();
                }
            }
        });
    }

</script>