/**
 * Created by shenyong on 2016/11/29.
 */
$(function(){

});
/**
 *
 * 初始化品牌 车系 车系 厂商 年款
 *
 *  必须先有select框 才能初始化成功
 *
 * brandElement 品牌select元素 id
 * seriesElement 车系select元素 id  厂商ElementID 为  seriesElement+“Factory”
 * modelElement  车型select元素 id  年款ElementID 为  modelElement+“Year”
 * brandId 品牌id  （可选）
 * factoryId 厂商ID（可选）
 * seriesId 车系ID （可选）
 * carYear 年款ID  （可选）
 * modelId 车型ID  （可选）
 *
 * demo:
 *     initBrand("brand","series","model",vhcl.brandId,vhcl.factoryId,vhcl.familyId,vhcl.carYear,vhcl.modelId);
 *
 * @author  yong.shen@carking001.com
 */
function initBrand(brandElement,seriesElement,modelElement,brandId,factoryId,seriesId,carYear,modelId){
	queryBrand(brandElement,seriesElement,modelElement,brandId,factoryId,seriesId,carYear,modelId);
	//console.info(brandElement+" = "+seriesElement+" = "+modelElement+" = "+brandId+" = "+factoryId+" = "+seriesId+" = "+carYear+" = "+modelId)
}
/**
 * 查询品牌
 * @param brandElement 品牌select元素 id
 * @param  seriesElement 车系select元素 id  厂商ElementID 为  seriesElement+“Factory”
 * @param  modelElement  车型select元素 id  年款ElementID 为  modelElement+“Year”
 * @param  brandId 品牌id  （可选）
 * @param  factoryId 厂商ID（可选）
 * @param  seriesId 车系ID （可选）
 * @param  carYear 年款ID  （可选）
 * @param  modelId 车型ID  （可选）
 */
function queryBrand(brandElement,seriesElement,modelElement,brandId,factoryId,seriesId,carYear,modelId) {
	$.ajax({
		type: "GET",
		url: basePath+"/portal/admin/model/brand.shtml",
		dataType: 'json',
		success: function (data) {
			for (var i in data.brands) {
				for (var j in data.brands[i]) {
					var brand = data.brands[i][j];
					if(brand.id != null ){
						if(brand.id != brandId) {
							$("#" + brandElement).append("<option value='" + brand.id + "'>" + i + "·" + brand.name + "</option>");
						}else{
							$("#" + brandElement).append("<option value='" + brand.id + "' selected>" + i + "·" + brand.name + "</option>");
						}
					}
				}
			}

			/*品牌点击事件*/
			$('#'+brandElement).on('change', function () {
				var brandid = $(this).val();
				showSeries(seriesElement,brandid,modelElement,factoryId,seriesId,carYear,modelId);
				$("#" + seriesElement).html("<option value=''>请选择</option>");
				$("#" + seriesElement+"Factory").html("<option value=''>请选择</option>");
				$("#" + modelElement).html("<option value=''>请选择</option>");
				$("#" + modelElement+"Year").html("<option value=''>请选择</option>");

				//console.info(brandElement+" = "+seriesElement+" = "+modelElement+" = "+brandId+" = "+factoryId+" = "+seriesId+" = "+carYear+" = "+modelId)
			});
			if(null!= brandId && ""!= brandId) {
				$("#" + brandElement).change();
				//console.info("brand change")
			}
		}
	});
}
/**
 * 展示车系
 */
function showSeries(seriesElement,brandid,modelElement,factory_id,seriesId,carYear,modelId){
	//console.info(seriesElement+" - "+brandid+" - "+modelElement+" - "+factory_id+" - "+seriesId+" - "+carYear+" - "+modelId)
	$.ajax({
		type:"get",
		url:basePath+"/portal/admin/model/family.shtml?brandId="+brandid,
		dataType:'json',
		success:function (data) {

			for (var i in data.factories) {//先循环 厂商
				var factory = data.factories[i];
				if(factory.id != null  ){
					if(factory_id != factory.id){
						$('#'+seriesElement+"Factory").append("<option value='" + factory.id + "'>" +factory.name + "</option>");
					}else{
						$('#'+seriesElement+"Factory").append("<option value='" + factory.id + "' selected>" +factory.name + "</option>");
					}

					$('#' + seriesElement).append("<option value='" + factory.id + "' disabled='disabled'>厂商：" +factory.name + "</option>");

					for(var i in data.families){//在循环 车系 吧厂商加到车系 下拉框里面
						for(var t in data.families[i]){
							if(factory.id == t) {
								for (var j in data.families[i][t]) {
									var family = data.families[i][t][j];
									if (family.seriesIds != null) {
										var isSelect = false;
										// family.seriesIds 因为数据库改版去掉车系组概念所以这里改成 车系id
										//var c = family.seriesIds.split(",");
										//for(var x = 0 ; x < c.length ; x++){
										//	if(c[x] == seriesId){
										//		isSelect = true;
										//	}
										//}
										if(family.seriesIds == seriesId){
											isSelect = true;
										}
										if(!isSelect){
											$('#' + seriesElement).append("<option value='" + t +","+family.seriesIds + "'>" + family.seriesName + "</option>");
										}else{
											$('#' + seriesElement).append("<option value='" + t +","+ family.seriesIds + "' selected='selected'>" + family.seriesName + "</option>");
										}
									}
								}
							}
						}
					}

				}
			}
			$('#' + seriesElement+"Factory").unbind("change");//這里会重复绑定事件 所以先清除在绑定
			$('#'+seriesElement+"Factory").on('change', function () {
				var factoryId = $(this).val();
				//选择厂商 筛选车系
				$('#' + seriesElement).find("option").each(function(i,item){
					var value = $(item).attr("value");
					if(factoryId != null && factoryId != "" && value.indexOf(factoryId) == 0 || value==""){
						$(item).show();
					}else{
						$(item).hide();
					}
				});

				$("#" + seriesElement).val("");
				$("#" + modelElement).html("<option value=''>请选择</option>");
				$("#" + modelElement+"Year").html("<option value=''>请选择</option>");

				//console.info(seriesElement+" = "+modelElement+" = "+factory_id+" = "+seriesId+" = "+carYear+" = "+modelId)
			});
			$('#' + seriesElement).unbind("change");//這里会重复绑定事件 所以先清除在绑定
			/*车系点击事件*/
			$('#' + seriesElement).on('change', function () {
				var value = $(this).val();
				var factoryid = value.substring(0,value.indexOf(","));
				var seriesid = value.substring(value.indexOf(",")+1);
				$("#" + modelElement).html("<option value=''>请选择</option>");
				$("#" + modelElement+"Year").html("<option value=''>请选择</option>");
				if(factoryid!= null && factoryid != "" && seriesid != null && seriesid!= "" ) {
					showSpec(modelElement, brandid, factoryid, seriesid, carYear, modelId);
				}
				//console.info(seriesElement+" = "+modelElement+" = "+factory_id+" = "+seriesId+" = "+carYear+" = "+modelId)

			});
			//  回显
			if(seriesId != null && seriesId!= "" && seriesId!= "undefined") {
				//showSpec(modelElement,brandid,factory_id,seriesId,carYear,modelId);
				$('#' + seriesElement).change();
				//console.info("family change")
			}

		}
	});
}

function showSpec(modelElement,brandid,factoryid,seriesid,carYear,modelId){
	//console.info(modelElement+" = "+factoryid+" = "+seriesid+" = "+carYear+" = "+modelId)
	$.ajax({
		type:"get",
		url:basePath+"/portal/admin/model/list.shtml?brandId="+brandid+"&factoryId="+ factoryid +"&seriesIds="+seriesid,
		dataType:'json',
		success:function (data) {
			//设置年款下拉框
			for(var i in data.years){
				var years = data.years[i];
				if(!isFunction(years)) {
					if(years != carYear ){
						$('#' + modelElement + "Year").append("<option value='" + years + "'>" + years + "</option>");
					}else {
						$('#' + modelElement + "Year").append("<option value='" + years + "' selected>" + years + "</option>");
					}
					$('#' + modelElement).append("<option value='" + years + "' disabled='disabled'>" + years + "年款</option>");
				}
				//设置车型
				for(var i in data.models){
					for(var t in data.models[i]){
						if(years == t) {
							for (var j in data.models[i][t]) {
								var model = data.models[i][t][j];
								if (model.id != "" && model.id != null) {
									if(model.id != modelId) {
										$('#' + modelElement).append("<option value='" + t + ',' + model.id + "'>" +model.family+" "+ model.name + "</option>");
									}else{
										$('#' + modelElement).append("<option value='" + t + ',' + model.id + "' selected>" +model.family+" "+ model.name + "</option>");
									}
								}
							}
						}
					}
				}
			}

			$('#'+modelElement+"Year").on('change', function () {
				var yearId = $(this).val();
				//选择年份 筛选车系
				$('#' + modelElement).find("option").each(function(i,item){
					var value = $(item).attr("value");
					if(yearId != null && yearId != "" && value.indexOf(yearId) == 0 || value==""){
						$(item).show();
					}else{
						$(item).hide();
					}
				});
				$("#" + modelElement).val("");
				//console.info("caYear change")
			});
		}
	});
}














