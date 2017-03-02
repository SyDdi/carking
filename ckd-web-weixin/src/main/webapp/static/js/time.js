$(function(){

		function createDataHtml(year){
			console.info("111111")
			var thisYear = new Date().getFullYear();
            var thisMonth = new Date().getMonth();
			$(".intro_xzsj_lst").empty();
            //前面一年 ，后面8年         
	        for(i=(year-1);(i<=thisYear && i<=year+8);i++){
				var str = "<h> <span class=\"year\">"+i+"</span><ul>";
	            if(i==thisYear){
	                for(j=1;j<=thisMonth+1 && j<=12;j++) {
	                    str = str + "<li><span>" + j + "月</span></li>"
	                }
	            }else {
	                for (j = 1; j <= 12; j++) {
	                   str = str + "<li><span>" + j + "月</span></li>"
	                 }
	            }
	            str+="</ul></h>";
				$(".intro_xzsj_lst").append(str);
			}
		}
		function addEventForData(){
			$('.aside-time ul li')
					.bind(
					'click',
					function(e) {
						//window.location.href = "../../pinggu?"+Math.random();
						//window.location.href = "pinggu.html";
						var monthStr = parseInt($(e.target).text());
						if(monthStr<10){
							monthStr = "0"+monthStr;
						}
						var yearStr = $(e.target).parents('h').children('.year').text();

						setCookie('timeName', yearStr+ '年' + $(e.target).text() + '月');
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
						ynMover();
					});
		}

		function initData(){
			var myDate=new Date();
			var currentYear = myDate.getFullYear()-8;
			var modelId = getCookie('modelId');
			try{
				if(modelId!="" && modelId.split("_").length==6){
					var tmpYear = modelId.split("_")[4];
					if(tmpYear!="" && tmpYear.length==4){
						currentYear = parseInt(tmpYear);
					}
				}
			}catch(e){
//				alert(e);
			}
			//createDataHtml(currentYear);
			
			var modelName=$("#model").html();
			var yearEndIndex=modelName.indexOf("款");
			var carYear=modelName.substring(yearEndIndex-4,yearEndIndex);
			
			if(carYear!=null&&carYear!==""){
				createDataHtml(carYear);
			}
			addEventForData();
			
		}
		/*没有输入车型的话就禁止弹出*/
		$('#time').on('click',function(){
			if($('#modelId').val() != '' || $('modleId').val() != null){
				initData();
				$('.aside-time').css('transform', "translate3d(0,0,0)");
				setTimeout(function(){
					$('#zh').show();
				},50)
			}
			choiseDate();
			
		})
		/*效果*/
		$('.intro_xzsj_lst').bind('click', function(e) {
			$(e.target).children('ul').slideToggle();
			ynMover();
		});
		/*出现侧栏时页面不能滑动*/
		function ynMover(){
			if($("#zh").css("display") == "block"){
				$('html,body').addClass('ovfHiden');
			}else if($("#zh").css("display") == "none"){
				$('html,body').removeClass('ovfHiden');
			}
		}


	
})
