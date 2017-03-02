$(function(){	
	var arr = [2,13,6,36,15,30,38,17,16,121];
    var portalUrl = ckdConfig.portal;
	/*加载品牌侧栏*/
	function showBrand() {
		//setTimeout(function () {
			$.ajax({
				url: portalUrl + "/api/model/brand",
				type: "get",
				dataType: 'jsonp',
				jsonp: "callback",
				jsonpCallback: "success_jsonpCallback",
				success: function (data) {
					for (var i in data.data.brands) {
						$('.intro_fd_zm ul').append('<li><a href="#intro_fhys01_' + i + '">' + i + '</a></li>');
						$('#content').append('<section><div class="intro_fhys01" name="intro_fhys01_' + i + '" id="intro_fhys01_' + i + '">' + i + '</div></section>');
						for (var j in data.data.brands[i]) {
							$('#intro_fhys01_' + i).after('<section><div data-brandid="' + data.data.brands[i][j].id + '"class="intro_pp_lst"><dl><dt><img src="/static/images/intro_pp_lst' + data.data.brands[i][j].pic + '.png"></dt><dd>' + data.data.brands[i][j].name + '</dd></dl></div></section>');
							for (var k in arr) {
								if (data.data.brands[i][j].pic == arr[k]) {
									$('.intro_pp_rm ul').append('<li><a data-brandid="' + data.data.brands[i][j].id + '" href="javascript:;"><dl><dt><img src="/static/images/intro_pp_lst' + data.data.brands[i][j].pic + '.png"></dt><dd>' + data.data.brands[i][j].name + '</dd></dl></a></li>')
								}
							}
						}
					}

					/*品牌点击事件*/
					$('#content .intro_pp_lst,.intro_pp_rm a').on('click', function () {
						var brandid = $(this).data('brandid');
						var pin = $(this).text();
						$('.aside-Series').css('transform', "translate3d(0,0,0)");
						showSeries(brandid, pin);
					});
				},
				error: function (er, XMLHttpRequest, textStatus, errorThrown) {
					BackErr(er);
					//alert(XMLHttpRequest.status);
					//alert(XMLHttpRequest.readyState);
					//alert(textStatus);
					//alert(errorThrown);
				}
			});
		//}, 200)
	}
	var thisPage = true;
	$('#model').on('click',function(){
		if(thisPage == true){
			showBrand();
			thisPage = false;
		}

	})

	/*展示车系*/
		function showSeries(brandid,pin){
			/*1207*/
			$('.aside-Series .menu_mc').find('dt').text(pin);
			//setTimeout(function(){
			$.ajax({
				type:"get",
				url:portalUrl+"/api/model/family?brandId="+brandid,
				dataType:'jsonp',
				jsonp:"callback",
	  			jsonpCallback:"success_jsonpCallback",
	  			success:function (data) {
	  				/*1207*/
	  				$('.aside-Series .intro_pp_xzcx .L ul').empty();
	  				$('.aside-Series .intro_pp_xzcx .R').empty();
	  				
	  				for(var i in data.data.factories){
	  					
	  					$('.aside-Series .intro_pp_xzcx .L ul').append('<li><a href="javascript:;" id="'+ data.data.factories[i].id +'">'+data.data.factories[i].name+'</a></li>');
	  					
	  				}
	  				
	  				for(var i in data.data.families){
	  					for(var t in data.data.families[i]){
	  						$('.aside-Series .intro_pp_xzcx .R').append('<ul class="num'+ t +'"></ul>');	
	  						for(var j in data.data.families[i][t]){
	      						$('.num'+t).append('<li><a href="javascript:;" data-seriesids="'+ data.data.families[i][t][j].seriesIds +'">'+data.data.families[i][t][j].seriesName+'</a></li>');
	      					}
	  					}
						
	  				}
	  				
	  				/*默认显示样式*/
	  				$(".aside-Series .intro_pp_xzcx .L a").eq(0).css("background","#DFDFDF");
	  				$(".aside-Series .intro_pp_xzcx .R ul").eq(0).css("display","block");
	  				var fid = $(".aside-Series .intro_pp_xzcx .L a").eq(0).attr('id');
	  				$('.aside-Series .intro_pp_xzcx .R ul').eq(0).find('a').attr('id',fid);
	  				
	  				/*车系切换显示点击事件*/
	  				$(".aside-Series .intro_pp_xzcx .L a").click(function(){
						$(this).css("background","#DFDFDF").parent().siblings().children().css("background","#F1F1F1");
						var seriesIndex = $(this).attr('id');
						$('.aside-Series .intro_pp_xzcx .R a').attr('id',seriesIndex);
						$(".aside-Series .intro_pp_xzcx .R .num" + seriesIndex).css("display","block").siblings().css("display","none");
					})
	  				/*车系点击事件*/
	  				$('.aside-Series .intro_pp_xzcx .R a').on('click', function () {
	  					var factoryid = $(this).attr('id');
				        var seriesid = $(this).data('seriesids');
				        var name = $(this).text();
				        $('.aside-model').css('transform',"translate3d(0,0,0)");
				        showSpec(brandid,seriesid,factoryid,name,pin);
			    	});

					var hg_height = $(document.body).height()-48;
					$(".intro_pp_xzcx .L ul").css("height", hg_height);
					$(".intro_pp_xzcx .R ul").css("height", hg_height);
	  			}
				
			});
			//},200)
		}
	
	/*展示车型*//*1207*/
		function showSpec(brandid,seriesid,factoryid,name,pin){
			$('.aside-model .menu_mc').find('dt').text(name);
			//setTimeout(function(){
			$.ajax({
				type:"get",
				url:portalUrl+"/api/model/list?brandId="+brandid+"&factoryId="+ factoryid +"&seriesIds="+seriesid,
				dataType:'jsonp',
				jsonp:"callback",
	  			jsonpCallback:"success_jsonpCallback",
	  			success:function (data) {
					$('.aside-model .intro_pp_xzcx .L ul').empty();
	  				$('.aside-model .intro_pp_xzcx .R').empty();

	  				for(var i in data.data.years){
	  					$('.aside-model .intro_pp_xzcx .L ul').append('<li><a href="javascript:;" id="'+ data.data.years[i] +'">'+data.data.years[i]+'</a></li>');
	  				}
	  				for(var i in data.data.models){
	  					for(var t in data.data.models[i]){

	  						$('.aside-model .intro_pp_xzcx .R').append('<ul data-year="'+ t +'"class="num'+ t +'"></ul>');
	  						for(var j in data.data.models[i][t]){
								var family =  data.data.models[i][t][j].family;
								if (family == name){  //如果是车系组 就显示车系 否则不显示车系
									family = "";
								}
								$('.num'+t).append('<li class="modelli"><dl><dt id="'+  data.data.models[i][t][j].id +  '">'+family +' '+ data.data.models[i][t][j].name+'</dt><dd></dd></dl></li>');
								//<li><dl><dt>1.6L 手自一体 豪华型</dt><dd></dd></dl></li>
	  						}
	  					}
	  				}
					/*默认显示样式*/
					$(".aside-model .intro_pp_xzcx .L a").eq(0).css("background","#DFDFDF");
					$('.aside-model .intro_pp_xzcx .R ul').eq(0).css("display","block");
	  				var fid = $(".aside-model .intro_pp_xzcx .L a").eq(0).attr('id');
	  				$('.aside-model .intro_pp_xzcx .R ul').eq(0).find('a').attr('id',fid);

					/*车型切换显示点击事件*/
	  				$(".aside-model .intro_pp_xzcx .L a").click(function(){
						$(this).css("background","#DFDFDF").parent().siblings().children().css("background","#F1F1F1");
						var modelIndex = $(this).attr('id');
						$('.aside-model .intro_pp_xzcx .R a').attr('id',modelIndex);
						$(".aside-model .intro_pp_xzcx .R .num" + modelIndex).css("display","block").siblings().css("display","none");
					})
	  				/*储存modeli的id和name*/
	  				$('.modelli').bind('click', function(e) {
						//window.location.href = "/pinggu?"+Math.random();

						/*车辆年份*/
						var year = $(this).parent().data('year');
						/*点击车型后直接显示在主页*/
						var carText = year+"款  "+ pin +' '+ name+' '+$(this).find("dt").text();
						/*显示在车辆型号*/
						if($('.aside-license').length > 0){
							$('#model').find('dd').text(carText);
						}else{
							$('#model').text(carText);
						}/*1208*/
						/*modeli的id*/
						var value = $(this).find("dt").attr("id");
						/*存储到cookie里的modelId*/
						var modelId = brandid +'_'+ factoryid + '_' + name + '_' + seriesid + '_' + year + '_' + value; 

						removeCookie('modelName');
						removeCookie('modelId');
						
						/*判断是最初录入页面还是基本信息页面*/
						if($('.aside-license').length > 0){
							$("#modelId").val(value);
							$("#modelName").val(carText);
							
							setCookie('modelName', carText);
							setCookie('modelId',value);
						}else{
							$("#modelId").val(modelId);
							$("#modelName").val(carText);
							
							setCookie('modelName', carText);
							setCookie('modelId',modelId);
						}
						showTime(year);/*显示上牌时间*/
						$('.aside-model').css('transform','translate3d(100%,0,0)');
						$('.aside-Series').css('transform','translate3d(100%,0,0)');
						$('.aside').css('transform','translate3d(100%,0,0)');
						$('#zh').hide();
						ynMover();
					});

					var hg_height = $(document.body).height()-48;
					$(".intro_pp_xzcx .row .L ul").css("height", hg_height);
					$(".intro_pp_xzcx .row .R ul").css("height", hg_height);
	  			}
			});
			//},200)
		}
	/*显示上牌时间*/
	function showTime(year){
		/*选择车型后显示年份*/
		var currentYear = new Date().getFullYear();
	    var currentMonth = new Date().getMonth();
	    $(".intro_xzsj_lst").empty();
	    for(m=(year-1);(m<=currentYear && m<=year+8);m++){
			var str = "<h> <span class=\"year\">"+m+"</span><ul>";
	        if(m==currentYear){
	            for(n=1;n<=currentMonth+1 && n<=12;n++) {
	                str = str + "<li><span>" + n + "月</span></li>"
	            }
	        }else {
	            for (n = 1; n <= 12; n++) {
	               str = str + "<li><span>" + n + "月</span></li>"
	             }
	        }
	        str+="</ul></h>";
			$(".intro_xzsj_lst").append(str);
		}
		/*$('.intro_xzsj_lst').bind('click', function(e) {
			$(e.target).children('ul').slideToggle();
		});*/
		$('.aside-time ul li')
				.bind(
				'click',
				function(e) {
					var monthStr = parseInt($(e.target).text());
					if(monthStr<10){
						monthStr = "0"+monthStr;
					}
					var yearStr = $(e.target).parents('h').children('.year').text();
					setCookie('time', yearStr+ '年' + $(e.target).text());
					setCookie('timeId', yearStr+ '-' + monthStr);
					if($('.aside-license').length > 0){
						$('#time dd').text(yearStr+'年'+monthStr+'月');
					}else{
						$('#time').text(yearStr+'年'+monthStr+'月');
					}
					$('#timeName').val(yearStr+'年'+monthStr+'月');
					$('#timeId').val(yearStr+ '-' + monthStr);
					$('.aside-time').css('transform',"translate3d(100%,0,0)");
					$('#zh').hide();
				});
		
		
	}
	/*点击显示侧边页面和退出效果*/
	function showYe(aclick,zhid,hide,asideid,shut_aside,removeUl){
			$(aclick).on('click', function(e) {
				setTimeout(function(){
					$(zhid).show();
				},200)
				if($(this).attr("id")!='shut-province'&&$(this).attr("id")!='shut-license-region'){
					$(hide).hide();
				}
				$(asideid).css('transform', "translate3d(0,0,0)");
				$(shut_aside).css('transform',"translate3d(100%,0,0)");
				$(removeUl).remove();
				ynMover();
			})
	
		};
	/*选择品牌和上牌时间显示*/
	showYe('#model','#zh','','.aside','','');
	/*showYe('#time','#zh','','.aside-time','','');*/
	showYe('#city','#zh','','.aside-city','','');
	showYe('#color','#zh','','.aside-color','','');
	showYe('#paizhao','#zh','','.aside-license','','')
	/*选择品牌/车系/车型/上牌时间的退出效果*/
	showYe('#shut','','#zh','','.aside','');
	showYe('#shut-Series','','','','.aside-Series','.aside-Series .intro_pp_xzcx .R ul');
	showYe('#shut-factory','','','','.aside-model','.aside-model .intro_pp_xzcx .R ul');
	showYe('#shut-time','','#zh','','.aside-time','');
	showYe('#shut-city','','#zh','','.aside-city','');
	showYe('#shut-color','','#zh','','.aside-color','');
	showYe('#shut-province','','#zh','','.aside-province','');
	showYe('#shut-license','','#zh','','.aside-license','');
	showYe('#shut-license-region','','#zh','','.aside-license-region','');
	/*遮罩层点击效果*/
	showYe('#zh','',"#zh",'','.aside','');
	showYe('#zh','',"#zh",'','.aside-Series','');
	showYe('#zh','',"#zh",'','.aside-model','');
	showYe('#zh','',"#zh",'','.aside-time','');
	showYe('#zh','',"#zh",'','.aside-city','');
	showYe('#zh','',"#zh",'','.aside-province','');
	showYe('#zh','',"#zh",'','.aside-color','');
	showYe('#zh','',"#zh",'','.aside-license','');
	showYe('#zh','',"#zh",'','.aside-license-region','');
	/*选择颜色点击事件*/
	$('.aside-color ul > li').bind('click', function(e) {
		//window.location.href = "../../pinggu?"+Math.random();
	    var text = $(this).find("dd").text();
		var value = $(this).find("dd").attr("id");
		setCookie('colorName', text);
		setCookie('colorId', value);
		$('#colorName').val(text)
		$('#colorId').val(value)
		if($('.aside-license').length > 0){
			$('#color dd').text(text);
		}else{
			$('#color').text(text);
		}
		$('.aside-color').css('transform','translate3d(100%,0,0)');
		$('#zh').hide();
		ynMover();
	});
	/*出现遮罩时不能滑动*/
	$('#zh').bind("touchmove",function(e){
		e.preventDefault();
	});
	
	/*if (getCookie('city')) {
	$('#city').text(getCookie('city'));
	}
	if (getCookie('time')) {
		$('#time').text(getCookie('time'));
	}
	if (getCookie('modelName')) {
		$('#model').text(getCookie('modelName'));
	}
	if (getCookie('colorName')) {
		$('#color').text(getCookie('colorName'));
	}
	if (getCookie('mile')) {
	    $('#mile').val(getCookie('mile'));
	}*/
	
	//$("#colorId").val(getCookie('colorId'));
	//$("#colorName").val(getCookie('colorName'));
	//$("#cityId").val(getCookie('cityId'));
	//$("#cityName").val(getCookie('city'));
	//$("#timeId").val(getCookie('timeId'));
	//$("#timeName").val(getCookie('time'));
	//$("#modelId").val(getCookie('modelId'));
	//$("#modelName").val(getCookie('modelName'));
})

/*设置用户选择信息的cookie*/
function choiseDate(){
	//var url = "/static/popup/gz_date.html?"+Math.random();
	var modelId = getCookie('modelId');
	if(modelId==undefined || modelId == ""){
		alert("请先选择车型");
	}else{
		//window.location.href = url;
	}
	return false;
}

function checkForm(frm) {
	//alert(frm.cityId.value);
    if (frm.modelId.value == null || frm.modelId.value == '') {
		alertMsg("请选择车辆型号");
        return false;
    }
    if (frm.timeId.value == null || frm.timeId.value == '') {
		alertMsg("请选择上牌时间");
        return false;
    }

    if (frm.cityId.value == null || frm.cityId.value == '') {
		alertMsg("请选择所在城市");
        return false;
    }
    if (frm.mile.value == null || frm.colorId.value == '') {
		alertMsg("请选择车辆颜色");
        return false;
    }
    if(frm.mile.value == null || frm.mile.value == ''){
		alertMsg("请输入行驶里程");
		return false;
    }else if(isNaN(frm.mile.value)) {
		alertMsg("行驶里程必须为数字");
        return false;
    }else{
        var reg = /^\d+(\.\d{1,2})?$/;
        if (!reg.test(frm.mile.value)) {
			alertMsg("行驶里程小数点最多2位");
            return false;
        }
    }
    setCookie("mile",frm.mile.value);
	return true;
};

/*出现侧栏时页面不能滑动*/
function ynMover(){
	if($("#zh").css("display") == "block"){
		$('html,body').addClass('ovfHiden');
	}else if($("#zh").css("display") == "none"){
		$('html,body').removeClass('ovfHiden');
	}
};

/*提醒框*/
function alertMsg(msg){
	var _height = $(window).height();
	$("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
	$("#collect_zs").text(msg)
	$("#collect_zs").delay(500).show().delay(1000).fadeOut(1000);
};

/*删除cookie*/
function removeCookie(name,value) {
	$.removeCookie(name);
};