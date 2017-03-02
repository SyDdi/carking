
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<form id="caredit_Report">
				<table class="table table-bordered">
					<tr>
						<th colspan="2" style="width: 80%;">车辆车况信息</th>
						<th  >
                            <div id="carReport_editDiv" style="display: none;">
                                <a onclick="carReport_save('caredit_Report');">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a onclick="carReportCancel('caredit_Report');">取消</a>
                            </div><a id="carReport_edit" onclick="carReport_edit(this,'caredit_Report');">编辑</a>
					</tr>
					<tbody id="carReport_table_tr"></tbody>
				</table>
            </form>
		</div>
	</div>
</div>

<style>
    .trd{background: #2f87c1;color: #edfbfb;}
</style>

<script >
	$(function(){
        findReport();
	});

    function findReport() {//查询车主信息
		$.ajax({
			type: "GET",
			url: "${basePath}/vehiclemanage/findReport.shtml",
			data: "vhclId="+$("#vhclId").val(),
			dataType: "json",
			success: function (code) {
				var noChoiceArray=new Array();
                var noChoiceIndex=0;
			
                if (code.result == 1) {
                    var findReport = code.data;
                    if (findReport != null) {
                        var issue = findReport.issue;
                        var trHtml;
                        // 遍历所有答案内容  做回显用
                        var daAnStr = $.parseJSON(findReport.answerIds);
                        var anIds = new Array();
                        var x = 0;
                        for (var t in daAnStr) {
                            var wen = daAnStr[t].issueId;
                            if (daAnStr[t].answerIds != null && daAnStr[t].answerIds != "") {
                                if (daAnStr[t].answerIds.indexOf(",")) {
                                    var xxx = daAnStr[t].answerIds.split(",");
                                    for (var i = 0; i < xxx.length; i++) {
                                        anIds[x] = xxx[i];
                                        x++;
                                    }
                                } else {
                                    anIds[x] = daAnStr[t].answerIds;
                                    x++;
                                }
                            }
                        }
                        //遍历 所有问题 以及答案html
                        $.each(issue, function (i, item) {//http://localhost:8080
                            var choice = item.choice;
                            trHtml += '<tr class="trd"><td colspan="3">' + issue[i].issueDesc + ' <input type=\"hidden\" class="issueClass" desc="'+issue[i].issueDesc+'" value="' + issue[i].id + '"/></td> </tr>';
                            var hasChecked=false;
                            $.each(choice, function (y, cho) {
                                //这里循环 reprot 答案 if 对比 答案表id 给input标签 加上选中
                                var isShow;
                                var isNone = "display: none;";
                                for (var p = 0; p < anIds.length; p++) {
                                    if (anIds[p] == cho.id) {
                                        isShow = true;
                                        hasChecked=true;
                                        if(cho.type == 1){
                                            isNone ="";
                                        }
                                    }
                                }
                                var choice = cho.choiceDesc;
                                var inputType = "<input type=\"radio\" name=" + issue[i].id + " value='" + cho.id + "'onclick='other(this," + cho.type + ")' isChoice='" + cho.type + "' " + (isShow ? 'checked' : '') + " desc='"+choice+"'>";
                                if (issue[i].isChoices == 1) {//判断是否多选
                                    inputType = "<input type=\"checkbox\" name=" + issue[i].id + " value='" + cho.id + "' " + (isShow ? 'checked' : '') + " desc='"+choice+"' onclick='other(this," + cho.type + ")'>";
                                }
                                if (cho.type == 1) {//判断是否其他类型
                                    var  typeDesc =  cho.typeDesc;
                                    if(issue[i].issueDesc.indexOf("电子设备")>0){
                                        typeDesc = findReport.electron;
                                    }else if(issue[i].issueDesc.indexOf("过户次数")>0){
                                        typeDesc = findReport.transfer;
                                    }
                                    choice += "<input type=\"text\" maxlength='150' style='width: 80%;"+isNone+" ' id='other_" + issue[i].id + "' name=" + issue[i].id + " onblur='updOther(this," + cho.id + ");' value=" + typeDesc + ">";
//                                    choice += "<input type=\"text\" maxlength='150' style='width: 80%;"+isNone+" ' id='other_" + issue[i].id + "' name=" + issue[i].id + " value=" + cho.typeDesc + ">";
                                }
                                trHtml += '<tr><td colspan="3">' + inputType + '&nbsp;' + choice + '</td></tr>';
                            });
                            
                            if(!hasChecked){
                                noChoiceArray[noChoiceIndex]=issue[i].id;
                                noChoiceIndex++;
                            }
                            
                        });
                        var updateTime = findReport.updateDate;
                        if ((updateTime == null || updateTime == "")&&(findReport.createDate == null || findReport.createDate == "")){
                        	updateTime = "--";
                        }else if (updateTime == null || updateTime == "") {
                            updateTime = getDate(findReport.createDate, "yyyy-MM-dd hh:mm:ss");
                        }else {
                            updateTime = getDate(findReport.updateDate, "yyyy-MM-dd hh:mm:ss");
                        }
                        trHtml += '<tr><td colspan="2"  class="trd">车况描述</td><td><a>生成</a></td></tr>';
                        trHtml += '<tr ><td colspan="3"><textarea rows="5" maxlength="500" style="width: 100%;" id="reportDesc" >' + ( findReport.reportDesc == null ? "" : findReport.reportDesc) + '</textarea></td></tr>';
                        trHtml += '<tr class="trd"><td style="width: 50%;">维护人员</td><td colspan="2" style="width: 50%;">更新时间</td></tr>';
                        trHtml += '<tr ><td>' + ( findReport.updateUserName == null ? "--" : findReport.updateUserName  ) + '</td><td  colspan="2">' + updateTime + '</td></tr>';
                        $("#carReport_table_tr").html(trHtml);
                    }else{
                        $("#carReport_table_tr").html('<tr><td colspan="3">暂无数据</td></tr>');
                    }
                } else {
                    alert("车辆分享信息查询失败-_-!");
                }
                carReportCancel("caredit_Report");
                
                
                for (var ind in noChoiceArray){
                	if(noChoiceArray[ind]!=8){
		            	$("input[name='"+noChoiceArray[ind]+"']").eq(0).attr("checked",'checked');
		            }
                }
                //$("input[name='"+9+"']").eq(0).attr("checked",'checked');
	            //alert("个"+$("input[name='"+1+"']").eq(1).attr("name"));
                
            }
		});
	}
    /**
     * 生成车况描述
     */
    function carReport_generate(formId){
        var reportDesc = "";
        var jsonItem,isshitu ;
        $("#"+formId).find(".issueClass").each(function(y,item){
            var issueValue = item.value;
            var desc = $(this).attr("desc");
            if(issueValue == 1 || issueValue == 9){ //重大事故 和 评级 不写
                reportDesc += "";
            }else if(issueValue == 4 || issueValue == 5 ){
//                reportDesc += (desc);
            }

            $("[name='" + issueValue + "']:checked").each(function(z,zitem){
                    jsonItem = zitem.value;
                var chenkedDesc = $(this).attr("desc");
//                console.info(desc +" - "+chenkedDesc);
                //reportDesc += (desc + chenkedDesc +"，");
                if(chenkedDesc != "未填写"){
                    if(issueValue == 2 ){ //外观
                        reportDesc += chenkedDesc+"，";
                    }else if(issueValue == 3 ){ //内饰
                        reportDesc += desc+chenkedDesc+"，";
                    }else if(issueValue == 4 ){ //发动机
                        if(reportDesc.indexOf("发动机")<1){
                            reportDesc += (desc);
                        }
                        reportDesc += chenkedDesc+"、";
                    }else if(issueValue == 5 ){ //变速箱
                        if(reportDesc.indexOf("变速箱")<1){
                            reportDesc += (desc);
                        }
                        reportDesc += chenkedDesc+"、";
                    }else if(issueValue == 6 ){ //电子设备
                        if(jsonItem== 28){
                            chenkedDesc = $("#other_"+issueValue).val()+"存在故障";
                        }
                        reportDesc +=chenkedDesc+"，";
                    }else if(issueValue == 7 && jsonItem == 30){ //4S店保养
                        reportDesc += "4S店保养，"
                    }else if(issueValue == 8 ){ //过户次数
                        reportDesc += /*desc+*/$("#other_"+issueValue).val()+"次过户";
                    }else if(issueValue == 1 && jsonItem == 5){ //重大事故
                        reportDesc += "";isshitu = 5;
                    }
                }
            });
        });

        if( isshitu == 5){ //重大事故
            reportDesc += "，事故车";
        }

        $("#reportDesc").val(reportDesc+"。");
    }
	function other(opt,type){
		var id =  $(opt).attr("name");
        var thisValue = $(opt).val();
        if(type == 1){
			$("#other_"+id).show();
		}else{
            $("#other_"+id).hide();
		}
	}


    function updOther(opt,choiceId){
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/updChoice.shtml",
            data: "choiceDesc=" +$(opt).val()  + "&id=" + choiceId,
            dataType: "json",
            success: function (code) {
//                alert(code.data);
            }
        });
    }

    
    /**
     * 编辑
     */
	function carReport_edit(opt,formId){
        $("#carReport_editDiv").show();
        $(opt).hide();
        $(':input','#'+formId).not(':button, :submit, :reset, :hidden').removeAttr('disabled');
        $("#carReport_table_tr a").attr('onclick',"carReport_generate(\'caredit_Report\');");
        
	}
    /**
     * 取消
     */
    function carReportCancel(formId){
        $("#carReport_editDiv").hide();
        $("#carReport_edit").show();
        $(':input','#'+formId).not(':button, :submit, :reset, :hidden').attr('disabled','disabled');
        $("#carReport_table_tr a").removeAttr('onclick');
    }

    /**
     * 保存修改
 	 * @param opt
     */
    function carReport_save(formId){
		var answerIds="[";
        var issues = "",electron ="",transfer="0";
        $("#"+formId).find(".issueClass").each(function(y,item){
            var issueValue = item.value;
            var jsonItem = "";
            $("[name='" + issueValue + "']:checked").each(function(z,zitem){
                if(z == 0){
                    jsonItem += zitem.value;
                }else {
                    jsonItem += "," + zitem.value;
                }
                if( zitem.value == 28 ) {
                    electron = $("#other_" + issueValue).val();
                }else if( zitem.value == 32) {
                    transfer = $("#other_" + issueValue).val();
                    if(transfer.indexOf("次过户")<0){
                        transfer += "次过户";
                    }
                }
            });
            issues += ","+issueValue;
            if(y == 0) {
                answerIds += '{"issueId":' + issueValue + ',"answerIds":"'+jsonItem+'"}';
            }else{
                answerIds += ',{"issueId":' + issueValue + ',"answerIds":"'+jsonItem+'"}';
            }
        });
        answerIds += "]";
        var answerJson = answerIds;
        var param = "vehicleId=" + $("#vhclId").val() + "&reportDesc=" + $("#reportDesc").val() +"&issueIds="+issues +"&answerIds="+answerJson +"&transfer="+transfer;
        if(electron != ""){
            param += "&electron="+electron+"存在故障"
        }
		if(confirm("是否保存数据！")){
            $.ajax({
                type: "GET",
                url: "${basePath}/vehiclemanage/saveReport.shtml",
                data:param ,
                dataType: "json",
                success: function (code) {
                    alert(code.data);
                    carReportCancel("caredit_Report");
                }
            });
		}else{
            findReport();
		}
    }
</script>