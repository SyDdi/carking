function foldDiv(obj) {
	var name = $(obj).attr("class");
	if (name == "icon-caret-down") {
		$(obj).parent().parent().parent().children("div").each(function() {
			var className = $(this).attr("class");
			if (className != 'portlet-title') {
				$(this).hide(500);
			}
		});
		$(obj).attr("class", "icon-caret-left");
	} else if (name == "icon-caret-left") {
		$(obj).parent().parent().parent().children("div").each(function() {
			var className = $(this).attr("class");
			if (className != 'portlet-title') {
				$(this).show(500);
			}
		});
		$(obj).attr("class", "icon-caret-down");
	}
}
function isGuoQi(d){
	if(d==null||d==''){
		return false;
	}
	var date1=new Date();  
	var date2=new Date(d); 
	var date3=date2.getTime()-date1.getTime();
	if(date3>0){
		var days=Math.floor(date3/(24*3600*1000));
		if(days<30){
			 return true;
		}
	}
	return false;
}
function getCode(type, codeValue, elementId) {
	$.ajax({
		type : "POST",
		url : "/bd/code/getCode.json",
		data : "type=" + type + "&codeValue=" + codeValue,
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			// console.log(str.code+"|"+elementId);
			returnStr(str, codeValue, elementId);
		}
	});

}

//公司 部门  通用方法  companyId 公司的的标签id  isCompany 是否是公司  selComId 选择的公司值 isAgencyCompany 是否是代理公司 departmentId 部门标签id selDepId选中的部门id 
function getComDePer(companyId,isCompany,selComId,isAgencyCompany,departmentId,selDepId) {
	getCompanyTwo(companyId,isCompany,selComId,isAgencyCompany);
	if(selComId!=null && ''!=selComId){
		getDepartment(selComId,selDepId,departmentId);  //绑定部门组织
		$('#'+companyId).change(function() { //给部门id 绑定onchang事件
			$('#'+departmentId).html("");    //清空部门
			getDepartment($(this).val(),selDepId,departmentId);  //绑定部门组织
		});
	} else {
		$('#'+companyId).change(function() { //给部门id 绑定onchang事件
			$('#'+departmentId).html("");    //清空部门
			getDepartment($(this).val(),selDepId,departmentId);  //绑定部门组织
		});
	}
}

//公司方法
function getCompanyTwo(elementId,isCompany,selComId,isAgencyCompany) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url : "/buy/contract/selectFinanceOrg.json",
		data : "isCompany="+isCompany+"&isAgencyCompany="+isAgencyCompany,
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			if ($('#' + elementId).is('select')) {
				var options = "<option value=''>--请选择--</option>";
				for ( var i = 0; i < str.mapList.length; i++) {
					if(str.mapList[i].financeOrgId == selComId){
						options += "<option selected='selected' value='" + str.mapList[i].financeOrgId
						+ "'>" + str.mapList[i].orgName
						+ "</option>";
					} else {
						options += "<option value='" + str.mapList[i].financeOrgId
						+ "'>" + str.mapList[i].orgName
						+ "</option>";
					}
				}
				$("#" + elementId).empty();
				$("#" + elementId).append(options);
			}
		}
	});
}

//部门组织方法
function getDepartment(companyValue,selDepId,elementId){
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/buy/contract/findByIdDepartment.json",
		data : "parentOrgId="+companyValue,
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			if ($('#' + elementId).is('select')) {
				var options = "<option value=''>--请选择--</option>";
				for ( var i = 0; i < str.mapList.length; i++) {
					if(str.mapList[i].financeOrgId == selDepId){
						options += "<option selected='selected' value='" + str.mapList[i].financeOrgId
						+ "'>" + str.mapList[i].orgName
						+ "</option>";
					} else {
						options += "<option value='" + str.mapList[i].financeOrgId
							+ "'>" + str.mapList[i].orgName
							+ "</option>";
					}
				}
				$("#" + elementId).empty();
				$("#" + elementId).append(options);
			}
		}
	});
}


//银行账户
function getBankAccount(bankName,elementId){
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	var companyId = "4";
	$.ajax({
		type : "POST",
		url:"/common/getBankAccount.json",
		data : "companyId="+companyId+"&bankName="+bankName,
		dataType : 'text',
		async : false,
		success : function(model) {
			$("#" + elementId).empty();
			var data = eval("(" + model + ")");
			for ( var i = 0; i < data.length; i++) {
				$("#" + elementId).append(
						"<option value='" + data[i].value + "'>" + data[i].name
								+ "</option>");
			}
		}
	});
}


//公司方法
function getCompany(elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/company/findAll.json",
		data : "",
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			if ($('#' + elementId).is('select')) {
				var options = "<option value=''>--请选择--</option>";
				for ( var i = 0; i < str.mapList.length; i++) {

					options += "<option value='" + str.mapList[i].companyId
							+ "'>" + str.mapList[i].companyShortname
							+ "</option>";
				}
				$("#" + elementId).empty();
				$("#" + elementId).append(options);
			}
		}
	});
}

function returnStr(str, codeValue, elementId) {
	if ($('#' + elementId).is('select')) {
		var num = parseInt(str.num);
		var options = "<option value=''>--请选择--</option>";
		for ( var i = 0; i < num; i++) {
			if (codeValue == str.codeList[i].codeValue) {
				options += "<option selected='selected' value='"
						+ str.codeList[i].codeValue + "'>"
						+ str.codeList[i].codeDesc + "</option>"
			} else {
				options += "<option value='" + str.codeList[i].codeValue + "'>"
						+ str.codeList[i].codeDesc + "</option>"
			}
		}
		$("#" + elementId).empty();
		$("#" + elementId).append(options);
	} else if ($('#' + elementId).is("input[type='text']")) {
		$("#" + elementId).val(str.codeList[0].codeDesc);
	} else if ($('#' + elementId).is('div')) {
		$("#" + elementId).text(str.codeList[0].codeDesc);
	}

}


//业务员方法
function findSales(elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url: "/intent/follow/findSales.json",
		data : "",
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			if ($('#' + elementId).is('select')) {
				var options = "<option value=''>--请选择--</option>";
				for ( var i = 0; i < str.mapList.length; i++) {

					options += "<option value='" + str.mapList[i].persoId
							+ "'>" + str.mapList[i].persoName
							+ "</option>";
				}
				$("#" + elementId).empty();
				$("#" + elementId).append(options);
			}
		}
	});
}

function getCodeDesc(codeValue) {
	if(codeValue==""&&codeValue==null){return;}
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	var strl = "";
	$.ajax({
		type : "POST",
		url:"/bd/code/getCode.json",
		data : "codeValue=" + codeValue,
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			// console.log(str.code+"|"+elementId);
			if (str.codeList[0] != null && str.codeList[0] != "") {
				strl = str.codeList[0].codeDesc;
			} else {
			}
		}
	});
	return strl;
}

//数字金额转中文大写
function moneyFormat4CN(money) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	var s;
	$.ajax({
		type : "POST",
		url:"/common/moneyFormat4CN.json",
		data : "money="+money,
		dataType : 'text',
		async : false,
		success : function(data) {
			s = eval('(' + data + ')');
		}
	});
	return s;
}


function getDealerDesc(dealerId) {
	var strl = ""
	if (dealerId != null && dealerId != "") {
		// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		// 获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		$.ajax({
			type : "POST",
			url:"/bd/dealer/getDealerDesc.json",
			data : "dealerId=" + dealerId,
			dataType : 'text',
			async : false,
			success : function(data) {
				if(data==""){return strl="";}
				var str = eval('(' + data + ')');
				// console.log(str.code+"|"+elementId);
				if (str.mapList != null && str.mapList != "") {
					strl = str.mapList.dealerName;
				} else {
				}
			}
		});
	}
	return strl;
}

// 生成radio 参数 radioname type（raido对应的codeType） elementId（放置raido的上级元素id）
function genRadio(raidoName, type, elementId, selRadioValue) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/bd/code/getCode.json",
		data : "type=" + type + "&codeValue=",
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			// console.log(str.code+"|"+elementId);
			createRadio(str, raidoName, elementId, selRadioValue);
		}
	});
}

// 生成raido
function createRadio(str, raidoName, elementId, selRadioValue) {
	var radioStr = "";
	var num = parseInt(str.num);
	var len = Math.floor(12/num);
	for ( var i = 0; i < num; i++) {
		if (selRadioValue == str.codeList[i].codeValue) {
			radioStr += "<div class='col-md-"+len+"'> <label style='cursor: pointer;font-size:13px' class='paymentType'>" 
					+ "<input type='radio' name='" + raidoName
					+ "' checked value='" + str.codeList[i].codeValue + "'/>"
					+ str.codeList[i].codeDesc
					+ "</label></div>";
		} else {
			radioStr += "<div class='col-md-"+len+"'> <label style='cursor: pointer;font-size:13px' class='paymentType'>" 
					+ "<input type='radio' name='" + raidoName + "' value='"
					+ str.codeList[i].codeValue + "'/>"
					+ str.codeList[i].codeDesc
					+ "</label></div>";
		}

	}
	$("#" + elementId).empty();
	$("#" + elementId).append(radioStr);
}

// 生成radio 参数 radioname type（raido对应的codeType） elementId（放置raido的上级元素id）
function genCheckBox(checkboxName, type, elementId, selCheckBoxValue) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/bd/code/getCode.json",
		data : "type=" + type + "&codeValue=",
		dataType : 'text',
		async : false,
		success : function(data) {
			var str = eval('(' + data + ')');
			// console.log(str.code+"|"+elementId);
			createCheckBox(str, checkboxName, elementId, selCheckBoxValue);
		}
	});
}

//生成可参选区域的checkBox
function getAreaCheckBox(checkboxName, elementId, selCheckBoxValue) {
	$.ajax({
		type : "POST",
		url : "/tenants/commission/queryAreaList.json",
		async : false,
		success : function(data) {
			createAreaCheckBox(data, checkboxName, elementId, selCheckBoxValue);
		}
	});
}


// 生成checkBox
function createAreaCheckBox(str, checkboxName, elementId, selCheckBoxValue) {
	var checkBoxStr = "";
	var num = parseInt(str.num);
	var len = Math.floor(12/num);
	for ( var i = 0; i < num; i++) {
		var selected = 0;
		if(selCheckBoxValue!=null&&selCheckBoxValue!=""){
			var sel = selCheckBoxValue.split(",");
			for (var j=0;j<sel.length;j++){
				if (sel[j] == str.orgList[i].orgCode) {
					selected = 1;
				}
			}
		}
		if (selected ==1) {
			checkBoxStr +=  "<label style='cursor: pointer;font-size:13px' class='paymentType'>"
				    +"<input type='checkbox' name='" + checkboxName
					+ "' checked value='" + str.orgList[i].orgCode + "'/>"
					+ str.orgList[i].orgDesc
			        + "</label>";
		}else if(selected ==0) {
			checkBoxStr +=  " <label style='cursor: pointer;font-size:13px' class='paymentType'>"
				    +"<input type='checkbox' name='" + checkboxName
					+ "' value='" + str.orgList[i].orgCode + "'/>"
					+ str.orgList[i].orgDesc
					 + "</label>";
		}

	}
	$("#" + elementId).empty();
	$("#" + elementId).append(checkBoxStr);
}	
// 生成raido
function createCheckBox(str, checkboxName, elementId, selCheckBoxValue) {
	var checkBoxStr = "";
	var num = parseInt(str.num);
	var len = Math.floor(12/num);
	for ( var i = 0; i < num; i++) {
		var selected = 0;
		if(selCheckBoxValue!=null&&selCheckBoxValue!=""){
			var sel = selCheckBoxValue.split(",");
			for (var j=0;j<sel.length;j++){
				if (sel[j] == str.codeList[i].codeValue) {
					selected = 1;
				}
			}
		}
		if (selected ==1) {
			checkBoxStr +=  "<div class='col-md-"+len+"'> <label style='cursor: pointer;font-size:13px' class='paymentType'>"
				    +"<input type='checkbox' name='" + checkboxName
					+ "' checked value='" + str.codeList[i].codeValue + "'/>"
					+ str.codeList[i].codeDesc
			        + "</label></div>";
		}else if(selected ==0) {
			checkBoxStr +=  "<div class='col-md-"+len+"'> <label style='cursor: pointer;font-size:13px' class='paymentType'>"
				    +"<input type='checkbox' name='" + checkboxName
					+ "' value='" + str.codeList[i].codeValue + "'/>"
					+ str.codeList[i].codeDesc
					 + "</label></div>";
		}

	}
	$("#" + elementId).empty();
	$("#" + elementId).append(checkBoxStr);
}

function getChildRegion(thisElementId, selRegionCode, nextElementId, nextValue) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getRegion.json",
		data : "regionCode=" + $("#" + thisElementId).val() + "&selRegionCode="
				+ selRegionCode,
		async : false,
		dataType : 'text',
		success : function(data) {
			var str = eval('(' + data + ')');
			$("#" + nextElementId).empty();
			$("#" + nextElementId).append(str.region);
			if(nextValue && str.region.indexOf(nextValue) >= 0){
				$("#" + nextElementId).val(nextValue);
			}
			$("#" + nextElementId).change();
		}
	});

}

// Id使用逗号分隔，一次查询，多次使用
function getProvince(elementIds) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getProvince.json",
		data : "",
		dataType : 'text',
		async:false,
		success : function(data) {
			var str = eval('(' + data + ')');
			var elmtArray = elementIds.split(',');
			for(var _ii = 0; _ii < elmtArray.length; _ii++){
				if(elmtArray[_ii]){
					$("#" + elmtArray[_ii]).empty();
					$("#" + elmtArray[_ii]).append(str.province);
				}
			}
		}
	});
}

// Id使用逗号分隔，一次查询，多次使用; 暂时对所有ID使用同一个并且调用一次回调函数
function getProvince(elementIds, callBack) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getProvince.json",
		data : "",
		dataType : 'text',
		async:false,
		success : function(data) {
			var str = eval('(' + data + ')');
			var elmtArray = elementIds.split(',');
			for(var _ii = 0; _ii < elmtArray.length; _ii++){
				if(elmtArray[_ii]){
					$("#" + elmtArray[_ii]).empty();
					$("#" + elmtArray[_ii]).append(str.province);
				}
			}
			if(callBack && typeof(eval(callBack)) == "function"){
				callBack();
			}
		}
	});
}

function getRegionName(regionId, elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getRegionDesc.json",
		data : "regionId=" + regionId,
		dataType : 'text',
		success : function(data) {
			var str = eval('(' + data + ')');
			$("#" + elementId).text(str.name);
		}
	});
}

function getOrgRegionName(elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getOrgRegionDesc.json",
		dataType : 'text',
		async : false,
		success : function(model) {
			$("#" + elementId).empty();
			var data = eval("(" + model + ")");
			$("#"+elementId).append("<option>ALL</option>");
			for ( var i = 0; i < data.length; i++) {
				$("#" + elementId).append(
						"<option value='" + data[i].value + "'>" + data[i].name
								+ "</option>");
			}
			
		}
	});
}

function getOrgSel(type,elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/report/purchase/purchaseRport/getOrg.json?type="+type,
		dataType : 'text',
		success : function(model) {
			$("#" + elementId).empty();
			var data = eval("(" + model + ")");
			if(data.orgList.length >= 1){
				$("#"+elementId).append("<option value=''>ALL</option>");
			}
			for ( var i = 0; i < data.orgList.length; i++) {
				$("#" + elementId).append(
						"<option value='" + data.orgList[i].orgId + "'>" + data.orgList[i].orgName
								+ "</option>");
			}
		}
	});
}

function getMyRegion(elementId) {
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	$.ajax({
		type : "POST",
		url:"/common/getMyRegion.json",
		dataType : 'text',
		success : function(model) {
			$("#" + elementId).empty();
			var data = eval("(" + model + ")");
			if(data.length >= 1){
				$("#"+elementId).append("<option value=''>ALL</option>");
			}
			for ( var i = 0; i < data.length; i++) {
				$("#" + elementId).append(
						"<option value='" + data[i].value + "'>" + data[i].name
								+ "</option>");
			}
		}
	});
}

function getSysCode(codeType, elementId){
 	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 $.ajax({
		 type: "POST",
         url:"/common/getSysCode.json",
         data : "codeType=" + codeType,
         dataType: 'text',
         success: function(model) {
			 $("#"+elementId).empty();
			 var data = eval("("+model+")");
			 $("#"+elementId).append("<option value=''>ALL</option>");
			 for(var i=0;i<data.length;i++) {
				 $("#"+elementId).append("<option value='"+data[i].value+"'>"+data[i].name+"</option>");
			 }
			}
      });
}

function getSysCode4Radio(codeType, elementId, radioName){
 	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 $.ajax({
		 type: "POST",
         url:"/common/getSysCode.json",
         data : "codeType=" + codeType,
         dataType: 'text',
         success: function(model) {
			 $("#"+elementId).empty();
			 var data = eval("("+model+")");
			 var radioStr="";
			 for(var i=0;i<data.length;i++) {
				 radioStr = radioStr+("<div class='col-md-3'> <label style='cursor: pointer;' class='paymentType'>" 
							+ "<input type='radio' name='" + radioName + "' value='"
							+ data[i].value + "'/>"
							+ data[i].name
							+ "</label></div>");
				// if(i=0)
				//	 $("#"+elementId + "input[type=radio]").attr("checked","true");
			 }
			 $("#"+elementId).append(radioStr);
		}
      });
}

function getSysCodeRadio(codeType, elementId, radioName,selRadioValue){
 	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 $.ajax({
		 type: "POST",
         url:"/common/getSysCode.json",
         data : "codeType=" + codeType,
         dataType: 'text',
         success: function(model) {
			 $("#"+elementId).empty();
			 var data = eval("("+model+")");
			 var radioStr="";
			 for(var i=0;i<data.length;i++) {
				 if (selRadioValue == data[i].value) {
					 radioStr = radioStr+("<div class='col-md-3'> <label style='cursor: pointer;' class='paymentType'>" 
								+ "<input type='radio' name='" + radioName
								+ "' checked value='" + data[i].value + "'/>"
								+ data[i].name
								+ "</label></div>");
				 }else{
					 radioStr = radioStr+("<div class='col-md-3'> <label style='cursor: pointer;' class='paymentType'>" 
								+ "<input type='radio' name='" + radioName + "' value='"
								+ data[i].value + "'/>"
								+ data[i].name
								+ "</label></div>");
				 }
			 }
			 $("#"+elementId).append(radioStr);
		}
      });
}

 
function querySysCode4CheckBox(condition1,condition2, elementId, boxName){
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 $.ajax({
		 type: "POST",
		 url:"/common/querySysCode.json",
         data : "condition1="+condition1+"&condition2="+condition2,
         dataType: 'text',
         success: function(model) {
			 $("#"+elementId).empty();
			 var data = eval("("+model+")");
			 var boxStr="";
			 for(var i=0;i<data.length;i++) {
				 boxStr = boxStr+("<div class='col-md-4'> <label style='cursor: pointer;' class='paymentType'>" 
							+ "<input type='checkbox' name='" + boxName + "' value='"
							+ data[i].value + "'/>"
							+ data[i].name
							+ "</label></div>");
			 }
			 $("#"+elementId).append(boxStr);
		}
      });
}

function fixNullString(value){
	var str = "";
	if(value != null && value != "null"){
		str = value;
	}
	return str;
}


// 第一个参数 日期字符串 可以是明显的日期时间格式,也可以是date类型的数字格式,
//第二个参数 格式化日期格式    类似“yyyy-MM-dd”,“yyyy-MM-dd HH:mm:ss”,分为带时分秒和不带的
function getDate(datastr, pattern) {
	 	var date;
		if((""+datastr).indexOf("-")==-1||!isNaN(datastr)){
			  date = new Date(datastr);
		}else{
			if(datastr.indexOf(":")>=0){
				var s = datastr.split(" "); var s1 = s[0].split("-"); var s2 = s[1].split(":");
				date = new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
			}else{
				var s = datastr.split("-");
				date = new Date(s[0],s[1],s[2]);
			}
		}
	    return date.format(pattern);
}
function showStr(str,len){
	if(str==null){
		return "";
	}
	if(str.length>=len){
		return str.substring(0,len)+"...";
	}
	return str;
}
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

Array.prototype.unique = function(){
	var res = [this[0]];
	for(var i = 1; i < this.length; i++){
		var repeat = false;
		for(var j = 0; j < res.length; j++){
			if(this[i] == res[j]){
				repeat = true;
				break;
			}
		}
		if(!repeat){
			res.push(this[i]);
		}
	}
	return res;
}

function msgAlert(msg) {
	$("#errorMessage-model-modal").remove();
	var str = '<div class="modal fade" id="errorMessage-model-modal" tabindex="-1" role="basic" aria-hidden="true">'
			+ '<div class="modal-dialog modal-message">'
			+ '<div class="modal-content">'
			+ '<div class="modal-body">'
			+ '<div class="portlet box yellow">'
			+ '<div class="portlet-title" >'
			+ '<div class="caption">提示信息</div>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>'
			+ '</div>'
			+ '<div class="portlet-body">'
			+ '<div class="alert alert-warning" >' + '<p>';
	str += msg;
	str += '</p>'
			+ '</div>'
			+ '<p align="right"><input type="submit" name="button" id="button" data-dismiss="modal" class="btn red" value="确认" /></p>'
			+ '</div>' + '</div>' + '</div>' + '</div>' + '</div>' + '</div>';
	$(".table-toolbar:last").append(str);
	$("#errorMessage-model-modal").modal("show");
}

function AllValidate(formId){
	$("#"+formId+" .help-block").each(function() {
		$(this).text("");
	});
	
	$("#"+formId+" .has-error").each(function() {
		$(this).removeClass("has-error");
	});
}

/**
 * 获取某个当前用户下拉框的区域和它的下级区域
 * @param elementId
 */
function getArea(elementId){
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	 $.ajax({
		 type: "POST",
         url:"/buy/contract/area.json",
         data : "",
         dataType: 'text',
         success: function(model) {
			 $("#"+elementId).empty();
			 var data = eval("("+model+")");
			 $("#"+elementId).append("<option value=''>ALL</option>");
			 for(var i=0;i<data.mapList.length;i++) {
				 $("#"+elementId).append("<option value='"+data.mapList[i].orgId+"'>"+data.mapList[i].orgName+"</option>");
			 }
			}
      });
}

function getAreaDesc(areaId){
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var str="";
	 $.ajax({
		 type: "POST",
         url:"/buy/contract/areaDesc.json",
         data : "orgId="+areaId,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 str=data.mapList[0].orgName;
			}
      });
	 return str;
}
//字符串格式化
//参数说明：num 要格式化的数字 n 保留小数位
function formatNum(num,n) {
	if(typeof(num) == "undefined"||num==""||num==0||num==null){
		num = 0;
	}
  num = String(num.toFixed(n));
  var re = /(-?\d+)(\d{3})/;
  while(re.test(num)) num = num.replace(re,"$1,$2")
  //return "¥"+num+"元";
  return ""+num+"";
}
//将数字转换成大写人民币
function uppercaseMoney(num) { //转成人民币大写金额形式 
	var str1 = '零壹贰叁肆伍陆柒捌玖'; //0-9所对应的汉字 
	var str2 = '万仟佰拾亿仟佰拾万仟佰拾元角分'; //数字位所对应的汉字 
	var str3; //从原num值中取出的值 
	var str4; //数字的字符串形式 
	var str5 = ''; //人民币大写金额形式 
	var i; //循环变量 
	var j; //num的值乘以100的字符串长度 
	var ch1; //数字的汉语读法 
	var ch2; //数字位的汉字读法 
	var nzero = 0; //用来计算连续的零值是几个 

	num = Math.abs(num).toFixed(2); //将num取绝对值并四舍五入取2位小数 
	str4 = (num * 100).toFixed(0).toString(); //将num乘100并转换成字符串形式 
	j = str4.length; //找出最高位 
	if (j > 15) {
		return '溢出';
	}
	str2 = str2.substr(15 - j); //取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分 

	//循环取出每一位需要转换的值 
	for (i = 0; i < j; i++) {
		str3 = str4.substr(i, 1); //取出需转换的某一位的值 
		if (i != (j - 3) && i != (j - 7) && i != (j - 11)
				&& i != (j - 15)) { //当所取位数不为元、万、亿、万亿上的数字时 
			if (str3 == '0') {
				ch1 = '';
				ch2 = '';
				nzero = nzero + 1;
			} else {
				if (str3 != '0' && nzero != 0) {
					ch1 = '零' + str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				}
			}
		} else { //该位是万亿，亿，万，元位等关键位 
			if (str3 != '0' && nzero != 0) {
				ch1 = "零" + str1.substr(str3 * 1, 1);
				ch2 = str2.substr(i, 1);
				nzero = 0;
			} else {
				if (str3 != '0' && nzero == 0) {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					if (str3 == '0' && nzero >= 3) {
						ch1 = '';
						ch2 = '';
						nzero = nzero + 1;
					} else {
						if (j >= 11) {
							ch1 = '';
							nzero = nzero + 1;
						} else {
							ch1 = '';
							ch2 = str2.substr(i, 1);
							nzero = nzero + 1;
						}
					}
				}
			}
		}
		if (i == (j - 11) || i == (j - 3)) { //如果该位是亿位或元位，则必须写上 
			ch2 = str2.substr(i, 1);
		}
		str5 = str5 + ch1 + ch2;

		if (i == j - 1 && str3 == '0') { //最后一位（分）为0时，加上“整” 
			str5 = str5 + '整';
		}
	}
	if (num == 0||typeof(num) == "undefined"||num=="") {
		str5 = '零元整';
	}
	return str5;
}

function DX(n) {
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据非法";
    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
        n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p+1, 2);
        unit = unit.substr(unit.length - n.length);
    for (var i=0; i < n.length; i++)
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}

function toUppercase(firstId,secondId){
	var value = "";
	if($("#"+firstId).is('div') || $("#"+firstId).is('span')){
		value = $("#"+firstId).text();
	}else{
		value = $("#"+firstId).val();
	}
	var up = "";
	if(value=="0" || value == ""){
		up = "零";
	} else {
		up = DX(Number(value));
	}
	if($("#"+secondId).is('div') || $("#"+secondId).is('span')){
		$("#"+secondId).text(up);
	}else{
		$("#"+secondId).val(up);
	}
}


function genFactory(factId,factValue){
    var str="";
	 $.ajax({
		 type: "POST",
         url:"/common/getFactory.json",
         data : "",
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.factList[i].length == 1){
					 str+="<optgroup label='"+data.factList[i]+"' style='font-size:14px;font-family:\"微软雅黑\";font-style:italic ;'></optgroup>";
				 }else {
					 if(data.factList[i] == factValue){
						 str+="<option selected value='"+data.factList[i]+"'>"+data.factList[i]+"</option>";
					 } else{
						 str+="<option value='"+data.factList[i]+"'>"+data.factList[i]+"</option>";
					 }
				 }
			 }
			 $("#"+factId).empty();
			 $("#"+factId).append(str);
			}
      });
	 return str;
}

function genBrand(factId,brandId,brandValue){
    var str="";
    var factValue = $("#"+factId).val();
	 $.ajax({
		 type: "POST",
         url:"/common/getBrand.json",
         data : "factName="+factValue,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.brandList[i] == brandValue){
					 str+="<option selected value='"+data.brandList[i]+"'>"+data.brandList[i]+"</option>";
				 } else{
					 str+="<option value='"+data.brandList[i]+"'>"+data.brandList[i]+"</option>";
				 }
			 }
			 $("#"+brandId).empty();
			 $("#"+brandId).append(str);
			}
      });
	 return str;
}

//获取所有品牌
function genBrands(brandId,brandValue){
    var str="";
	 $.ajax({
		 type: "POST",
         url:"/common/getAllBrand.json",
         data : "",
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.brandList[i].length == 1){
					 str+="<optgroup label='"+data.brandList[i]+"' style='font-size:14px;font-family:\"微软雅黑\";font-style:italic ;'></optgroup>";
				 }else {
					 if(data.brandList[i] == brandValue){
						 str+="<option selected value='"+data.brandList[i]+"'>"+data.brandList[i]+"</option>";
					 } else{
						 str+="<option value='"+data.brandList[i]+"'>"+data.brandList[i]+"</option>";
					 }
				 }
			 }
			 $("#"+brandId).empty();
			 $("#"+brandId).append(str);
			}
      });
	 return str;
}

function genSeries(brandId,seriesId,seriesValue){
    var str="";
    var brandValue = $("#"+brandId).val();
	 $.ajax({
		 type: "POST",
         url:"/common/getSeries.json",
         data : "brandName="+brandValue,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.seriseList[i] == seriesValue){
					 str+="<option selected value='"+data.seriseList[i]+"'>"+data.seriseList[i]+"</option>";
				 } else{
					 str+="<option value='"+data.seriseList[i]+"'>"+data.seriseList[i]+"</option>";
				 }
			 }
			 $("#"+seriesId).empty();
			 $("#"+seriesId).append(str);
			}
      });
	 return str;
}

function genCarYear(seriesId,carYearId,carYearValue){
    var str="";
    var seriesValue = $("#"+seriesId).val();
	 $.ajax({
		 type: "POST",
         url:"/common/getCarYear.json",
         data : "seriesName="+seriesValue,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.carYearList[i] == carYearValue){
					 str+="<option selected value='"+data.carYearList[i]+"'>"+data.carYearList[i]+"</option>";
				 } else{
					 str+="<option value='"+data.carYearList[i]+"'>"+data.carYearList[i]+"</option>";
				 }
			 }
			 $("#"+carYearId).empty();
			 $("#"+carYearId).append(str);
			}
      });
	 return str;
}

function genModelYear(seriesId,carYearId,carYearValue){
    var str="";
    var seriesValue = $("#"+seriesId).val();
	 $.ajax({
		 type: "POST",
         url:"/common/getModelYear.json",
         data : "seriesName="+seriesValue,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.carYearList[i] == carYearValue){
					 str+="<option selected value='"+data.carYearList[i]+"'>"+data.carYearList[i]+"</option>";
				 } else{
					 str+="<option value='"+data.carYearList[i]+"'>"+data.carYearList[i]+"</option>";
				 }
			 }
			 $("#"+carYearId).empty();
			 $("#"+carYearId).append(str);
			}
      });
	 return str;
}


function genModel(seriesId,modelId,modelValue){
    var str="";
    var seriesValue = $("#"+seriesId).val();
	 $.ajax({
		 type: "POST",
         url:"/common/getModel.json",
         data : "seriesName="+seriesValue,
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.listNum;i++){
				 if(data.modelList[i] == modelValue){
					 str+="<option selected value='"+data.modelList[i]+"'>"+data.modelList[i]+"</option>";
				 } else{
					 str+="<option value='"+data.modelList[i]+"'>"+data.modelList[i]+"</option>";
				 }
			 }
			 $("#"+modelId).empty();
			 $("#"+modelId).append(str);
			}
      });
	 return str;
}
 
//财务审核银行账户
function getBankAccount4Finance(companyId,bankName,elementId){
	$.ajax({
		type : "POST",
		url:"/common/getBankAccount.json",
		data : "companyId="+companyId+"&bankName="+bankName,
		dataType : 'text',
		async : false,
		success : function(model) {
			$("#" + elementId).empty();
			var data = eval("(" + model + ")");
			for ( var i = 0; i < data.length; i++) {
				$("#" + elementId).append(
						"<option value='" + data[i].value + "'>" + data[i].name
								+ "</option>");
			}
		}
	});
}


function genPg(pgId){
    var str="";
	 $.ajax({
		 type: "POST",
         url:"/common/getPg.json",
         data : "",
         dataType: 'text',
         async : false,
         success: function(model) {
			 var data = eval("("+model+")");
			 var str = "<option value=''>ALL</option>";
			 for (var i = 0 ;i <data.perNum;i++){
					 str+="<option value='"+data.perList[i].persoId+"'>"+data.perList[i].persoName+"</option>";
			 }
			 $("#"+pgId).empty();
			 $("#"+pgId).append(str);
			}
      });
	 return str;
}

//页面加载完成绑定重置按钮
$(document).ready(function (){
	$(".btn.btn-reset").click(function (){
		$(".table-toolbar.search-conditions").find("input").each(function (){
			if($(this).is("input[type='text']")){
				$(this).val("");
			} else if(($(this).is("input[type='checkbox']")||$(this).is("input[type='radio']"))&&$(this).attr("checked")){
				$(this).attr("checked",false);
			}
		});
		$(".table-toolbar.search-conditions").find("select").each(function (){
			$(this).val("");
		});
	});
});

/**
 * 判断是不是方法
 * @param fn
 * @returns {boolean}
 */
function isFunction(fn) {
	return Object.prototype.toString.call(fn)=== '[object Function]';
}