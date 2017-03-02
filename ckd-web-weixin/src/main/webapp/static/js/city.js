		/*加载页面时获取cookie*/
		function getHistoryCookie(classPath){
			var type=classPath.substring(1,classPath.length);
			var chooseCity=getCookie(type);
			if(chooseCity!=null){
				var array=chooseCity.split("@");
				for(var i=1;i<array.length;i++){
					var cityArray=array[i].split("$");
					showHistory(cityArray[0],cityArray[1],cityArray[2],cityArray[3],classPath);
				}
			}
		}
		/*历史选择放到cookie*/
		function saveCookie(cityId,cityName,provinceId,provinceName,classPath){
			var type=classPath.substring(1,classPath.length);
			var chooseCity=getCookie(type);
			//console.info("chooseCity: "+chooseCity);
			if(chooseCity!=null){
				var array=chooseCity.split("@");
				var repeat=false;
				for(var i=1;i<array.length;i++){
					if(array[i].split("$")[0]==cityId){
						array.splice(i,1);
						
						repeat=true;
					}
				}
				if(!repeat){
					if(array.length>=5){
						array.splice(1,1);
					}
					//setCookie(type,array.join("@")+"@"+cityId+"$"+cityName+"$"+provinceId+"$"+provinceName);
					//showHistory(cityId,cityName,provinceId,provinceName,classPath);
				}
				array.splice(array.length,0,cityId+"$"+cityName+"$"+provinceId+"$"+provinceName)
				setCookie(type,array.join("@"));
				
			}else{
				setCookie(type,type+"@"+cityId+"$"+cityName+"$"+provinceId+"$"+provinceName);
				//showHistory(cityId,cityName,provinceId,provinceName,classPath);
			}
			$(classPath+' .hideClass .intro_xzdq_lst ul').empty();
			getHistoryCookie(classPath);
		}
		
		/*省份显示*/
		var portalUrl = ckdConfig.portal;
		function requestZones(classPath){
			setTimeout(function(){
			$.ajax({
				url: portalUrl+"/api/zone/province",
				type : "get",
				dataType:'jsonp',
				jsonp:"callback",
          		jsonpCallback:"success_jsonpCallback",
				success : function(data) {
					/*删除原数据*/
					$('.aside-city .intro_xzdq_sf_lst').empty();
					$('.aside-license .intro_xzdq_sf_lst').empty();
					
					//console.info(result);
					var result = data.data;
					for(var i in result){
						$('.aside-city .intro_xzdq_sf_lst')
								.append(
										'<h id="province_' + result[i].id + '"><span id="'+ result[i].id +'"class="province_span">'
												+ result[i].name
												+ '</span><ul style="display: none" class="province_ul" id="province_ul_' + result[i].id + '"></ul></h>');
						if($('.aside-license').length > 0){
							$('.aside-license .intro_xzdq_sf_lst')
									.append(
										'<h id="province_' + result[i].id + '"><span id="'+ result[i].id +'"class="province_span">'
												+ result[i].name
												+ '</span><ul style="display: none" class="province_ul" id="province_ul_' + result[i].id + '"></ul></h>');
						}
												
					}
					$('.aside-city .intro_xzdq_sf_lst h').on('click', function(e) {
							var provinceid = $(this).find('span').attr('id');
							var hId = $(this).find('ul').attr('id');
							var pName = $(this).find('span').text();
							
							//console.info("省provinceid:"+provinceid);
							//console.info("hId:"+hId);
							//console.info("pName:"+pName);
							
							$('.aside-province .intro_xzdq_sf_lst ul').html('')
							cityProvince(pName,provinceid,hId)
							$('.aside-province').css('transform','translate3d(0,0,0)');
							
					});
					if($('.aside-license').length > 0){
						$('.aside-license .intro_xzdq_sf_lst h').on('click', function(e) {
							var provinceid = $(this).find('span').attr('id');
							var hId = $(this).find('ul').attr('id');
							var pName = $(this).find('span').text();
							
							$('.aside-license-region .intro_xzdq_sf_lst ul').html('')
							cityProvince(pName,provinceid,hId)
							$('.aside-license-region').css('transform','translate3d(0,0,0)');
							
						});
					}
					
					localCity(result,classPath);
				},
				error : function(er) {
					BackErr(er);
				}
			});
			},200)
		}
		/*所在城市历史选择点击*/
		function historyClickcity(cityId,cityName,provinceId,provinceName){
			saveCookie(cityId,cityName,provinceId,provinceName,'.aside-city');
			if($('.aside-license').length > 0){
				$('#city').find('dd').text(cityName);
			}else{
				$('#city').text(cityName);
			}
			$('#provinceId').val(provinceId);
			$('#cityId').val(cityId);
			$('#cityName').val(cityName);
			removeCookie('cityId');
			removeCookie('city');
			setCookie('cityId',cityId);
			setCookie('city',cityName);
			//$('#cityName').val(cityName);
			$('.aside-province').css('transform','translate3d(100%,0,0)');
			$('.aside-city').css('transform','translate3d(100%,0,0)');
			$('#zh').hide();
			ynMover()
		}
		/*牌照属地历史选择点击*/
		function historyClicklicense(cityId,cityName,provinceId,provinceName){
			saveCookie(cityId,cityName,provinceId,provinceName,'.aside-license');
			//alert("history()"+" cityId:"+cityId+" cityName"+cityName+" provinceId"+provinceId+" provinceName"+provinceName);
			$('#paizhao').find('dd').text(cityName);
			$('#regProvinceId').val(provinceId);
			$('#regZoneId').val(cityId);
			//$('#cityName').val(cityName);
			$('.aside-license-region').css('transform','translate3d(100%,0,0)');
			$('.aside-license').css('transform','translate3d(100%,0,0)');
			$('#zh').hide();
			ynMover()
		}
		
		/*历史选择显示*/
		function showHistory(cityId,cityName,provinceId,provinceName,classPath){
			var len=$(classPath+' .hideClass .intro_xzdq_lst ul li').length;
			//console.info("len:"+len);
			if($(classPath+' .hideClass .intro_xzdq_lst ul li').length>=4){
				$(classPath+' .hideClass .intro_xzdq_lst ul li:eq(3)').remove()
			}
			var path=classPath.substring(classPath.indexOf("-")+1,classPath.length);
			$(classPath+' .hideClass .intro_xzdq_lst ul')
				.prepend(
						'<li><span id =\"'+cityId+'\" onclick=\"'+'historyClick'+path+'('+cityId+',\''+cityName+'\','+provinceId+',\''+provinceName+'\')\">'
								+ cityName
								+ '</span></li>')
			
		}
		
		/*城市显示*/
		function cityProvince(pName,provinceId,hId){
			/*显示省份名称*/
			//console.info($('.aside-province').css('transform'))
			if($('.aside-province').css('transform')=='matrix(1, 0, 0, 1, 266, 0)'){
				$('.aside-province').find('dt').text(pName);
			}else if($('.aside-license-region').css('transform')=='matrix(1, 0, 0, 1, 266, 0)'){
				$('aside-license-region').find('dt').text(pName);
			}
			
			
			$.ajax({
				url:portalUrl+"/api/zone/list?provinceId="+provinceId,
				type : "get",
				dataType:'jsonp',
				jsonp:"callback",
          		jsonpCallback:"success_jsonpCallback",
				success : function(result) {
					var data = result.data;
					for ( var i in data) {
							$('.aside-province .intro_xzdq_sf_lst ul')
									.append(
											'<li><span id =\"'+data[i].id+'\">'
													+ data[i].name
													+ '</span></li>')
							$('.aside-license-region .intro_xzdq_sf_lst ul')
									.append(
											'<li><span id =\"'+data[i].id+'\">'
													+ data[i].name
													+ '</span></li>')
					}
				
					/*所在城市点击事件*/
					$('.aside-province .intro_xzdq_sf_lst span').on(
							'click',
							function(e) {
								var provinceName =$(this).text();
								var cityName = pName;
								
								//console.info("省份点击："+provinceName)//北京
								//console.info("城市点击："+cityName)//北京市
								//console.info("获取privinceId:"+provinceId);
								
								//selectCity( $(this).attr("id"),cityName,provinceName);
								if($('.aside-license').length > 0){
									$('#city').find('dd').text(provinceName);
								}else{
									$('#city').text(provinceName);
								}
								$('#provinceId').val(provinceId);
								$('#cityId').val($(this).attr("id"));
								$('#cityName').val(provinceName);
								$('.aside-province').css('transform','translate3d(100%,0,0)');
								$('.aside-city').css('transform','translate3d(100%,0,0)');
								/*放到历史选择里面*/
								//showHistory($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-city');
								saveCookie($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-city');
								
								removeCookie('cityId');
								removeCookie('city');
								setCookie('cityId',provinceId);
								setCookie('city',provinceName);
								
								$('#zh').hide();
								ynMover()
					});
					/*牌照属地点击事件*/
					$('.aside-license-region .intro_xzdq_sf_lst span').on(
							'click',
							function(e) {
								var provinceName =$(this).text();
								var cityName = pName;
								
								//console.info("拍照属地："+provinceName)//北京
								//console.info(cityName)//北京市
								
								//selectCity( $(this).attr("id"),cityName,provinceName);
								$('#paizhao').find('dd').text(provinceName);
								$('#regProvinceId').val(provinceId);
								$('#regZoneId').val($(this).attr("id"));
								$('.aside-license-region').css('transform','translate3d(100%,0,0)');
								$('.aside-license').css('transform','translate3d(100%,0,0)');
								//showHistory($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-license');
								saveCookie($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-license');
								
								$('#zh').hide();
								ynMover()
					});
				}
			})
		}
		
		function localCity(data,classPath){
			var geolocation = new BMap.Geolocation();
			var gc = new BMap.Geocoder();
			geolocation.getCurrentPosition( function(r) {   //定位结果对象会传递给r变量
						if(this.getStatus() == BMAP_STATUS_SUCCESS){  //通过Geolocation类的getStatus()可以判断是否成功定位。
							var pt = r.point;
							gc.getLocation(pt, function(rs){
								var addComp = rs.addressComponents;
								//console.log("定位："+addComp.province +"|"+ addComp.city  +"|"+ addComp.district  +"|"+ addComp.street  +"|"+ addComp.streetNumber);
								//$('.aside-main .mpT49 .mt10').text('定位城市： '+addComp.city.replace("市",""))

								for(var i=0;i<data.length;i++){
									//console.info("对比:"+JSON.stringify(data[i]))
									if(data[i].name!=null){
										//console.info(addComp.province);
										if(addComp.province==data[i].name){
											var provinceId=data[i].id;
											var provinceName=data[i].name;
											$.ajax({
												url:portalUrl+"/api/zone/list?provinceId="+data[i].id,
												type : "get",
												dataType:'jsonp',
												jsonp:"callback",
								          		jsonpCallback:"success_jsonpCallback",
												success : function(result) {
													var subData=result.data;
													
													for(var j=0;j<subData.length;j++){
														if(subData[j].name==addComp.city.replace("市","")){
															var cityId=subData[j].id;
															var cityName=subData[j].name;
															if(!getCookie('city')){
																/*评估页所在城市默认显示定位地区*/
																if($('.aside-license').length > 0){
																	$('#city').find('dd').text(cityName);
																}else{
																	$('#city').text(cityName);
																}

																$('#cityId').val(cityId);
																$('#cityName').val(cityName);
															}
															/*牌照属地默认显示所在地区定位*/
															if($('#regProvinceId').val() == "" || $('#regProvinceId').val() == null) {
																$('#regProvinceId').val(provinceId);
																$('#regZoneId').val(cityId);
																$('.basic_inf_02 #paizhao dd').text(cityName);
															}

															$(classPath+" .intro_fhys03").html("定位城市："+subData[j].name);
															//console.info("subData[j].id:"+subData[j].id+"data[i].id:"+provinceId);
															$(classPath+" .intro_fhys03").bind("click",function(){
																saveCookie(cityId,cityName,provinceId,provinceName,classPath);
																if(classPath=='.aside-license'){
																	$('#paizhao').find('dd').text(cityName);
																	$('#regProvinceId').val(provinceId);
																	$('#regZoneId').val(cityId);
																	$('.aside-license-region').css('transform','translate3d(100%,0,0)');
																	$('.aside-license').css('transform','translate3d(100%,0,0)');
																}else{
																	if($('.aside-license').length > 0){
																		$('#city').find('dd').text(cityName);
																	}else{
																		$('#city').text(cityName);
																	}
																	$('#provinceId').val(provinceId);
																	$('#cityId').val(cityId);
																	$('#cityName').val(cityName);
																	$('.aside-province').css('transform','translate3d(100%,0,0)');
																	$('.aside-city').css('transform','translate3d(100%,0,0)');
																	removeCookie('cityId');
																	removeCookie('city');
																	setCookie('cityId',cityId);
																	setCookie('city',cityName);
																}
																$('#zh').hide();
															});
															break;
														}
													}
												}
											})
										}
									}
								}

							});
						}else{
							//关于状态码
							//BMAP_STATUS_SUCCESS   检索成功。对应数值“0”。
							//BMAP_STATUS_CITY_LIST 城市列表。对应数值“1”。
							//BMAP_STATUS_UNKNOWN_LOCATION  位置结果未知。对应数值“2”。
							//BMAP_STATUS_UNKNOWN_ROUTE 导航结果未知。对应数值“3”。
							//BMAP_STATUS_INVALID_KEY   非法密钥。对应数值“4”。
							//BMAP_STATUS_INVALID_REQUEST   非法请求。对应数值“5”。
							//BMAP_STATUS_PERMISSION_DENIED 没有权限。对应数值“6”。(自 1.1 新增)
							//BMAP_STATUS_SERVICE_UNAVAILABLE   服务不可用。对应数值“7”。(自 1.1 新增)
							//BMAP_STATUS_TIMEOUT   超时。对应数值“8”。(自 1.1 新增)
							switch( this.getStatus() )
							{
								case 2:
									console.log( '位置结果未知 获取位置失败.' );
									break;
								case 3:
									console.log( '导航结果未知 获取位置失败..' );
									break;
								case 4:
									console.log( '非法密钥 获取位置失败.' );
									break;
								case 5:
									console.log( '对不起,非法请求位置  获取位置失败.' );
									break;
								case 6:
									console.log( '对不起,当前 没有权限 获取位置失败.' );
									break;
								case 7:
									console.log( '对不起,服务不可用 获取位置失败.' );
									break;
								case 8:
									console.log( '对不起,请求超时 获取位置失败.' );
									break;

							}
							$(".intro_fhys03").html("定位城市：无法定位");
						}
					},
					{enableHighAccuracy: true}
			);
		}

		$(function(){
			$('#city').on('click',function(){
				requestZones('.aside-city');
			})
			$('#paizhao').on('click',function(){
				requestZones('.aside-license');
			})
			
			/*加载cookie里历史选择城市*/
			getHistoryCookie('.aside-city');
			getHistoryCookie('.aside-license');
								
			
			/*所在城市热门城市点击事件*/
			$('.aside-city .hot_city_local .intro_xzdq_lst span').on(
					'click',
					function(e) {
						var provinceName =$(this).text();
						var cityName =$(this).attr("provinceName");
						var provinceId=$(this).attr("provinceId");
						//selectCity( $(this).attr("id"),cityName,provinceName);
						if($('.aside-license').length > 0){
							$('#city').find('dd').text(provinceName);
						}else{
							$('#city').text(provinceName);
						}
						$('#provinceId').val(provinceId);
						$('#cityId').val($(this).attr('id'));
						$('#cityName').val(provinceName);
						$('.aside-province').css('transform','translate3d(100%,0,0)');
						$('.aside-city').css('transform','translate3d(100%,0,0)');
						//showHistory($(this).attr("id"),$(this).text(),$(this).attr("provinceId"),$(this).attr("provinceName"),'.aside-city');
						saveCookie($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-city');
						removeCookie('cityId');
						removeCookie('city');
						setCookie('cityId',$(this).attr('id'));
						setCookie('city',provinceName);
						$('#zh').hide();
						//console.info("100=====");
						ynMover()
			});
			
			/*拍照属地热门城市点击事件*/
			$('.aside-license .hot_city_local .intro_xzdq_lst span').on(
					'click',
					function(e) {
						var provinceName =$(this).text();
						var cityName =$(this).attr("provinceName");
						var provinceId=$(this).attr("provinceId");
//						selectCity( $(this).attr("id"),cityName,provinceName);
						$('#paizhao').find('dd').text(provinceName);
						$('#regProvinceId').val(provinceId);
						$('#regZoneId').val($(this).attr("id"));
						$('#cityName').val(provinceName);
						$('.aside-license-region').css('transform','translate3d(100%,0,0)');
						$('.aside-license').css('transform','translate3d(100%,0,0)');
						//showHistory($(this).attr("id"),$(this).text(),$(this).attr("provinceId"),$(this).attr("provinceName"),'.aside-license');
						saveCookie($(this).attr("id"),$(this).text(),provinceId,cityName,'.aside-license');
						$('#zh').hide();
						//console.info("101");
						ynMover()
						
			});
		});

		/*出现侧栏时页面不能滑动*/
		function ynMover(){
			if($("#zh").css("display") == "block"){
				$('html,body').addClass('ovfHiden');
			}else if($("#zh").css("display") == "none"){
				$('html,body').removeClass('ovfHiden');
			}
		}
		/*删除cookie*/
		function removeCookie(name,value) {
			$.removeCookie(name);
	    }