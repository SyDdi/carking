<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckd" uri="http://tags.carking001.com/ckd" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<title>估值-行情价</title>
<link href="${ctx}/static/css/css.css?20161223" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/common/config.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/static/js/getscript.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/pinpai.js?time=20161223"></script>
	<script type="text/javascript" src="${ctx}/static/js/time.js?time=20161223"></script>
	<script type="text/javascript" src="${ctx}/static/js/city.js?time=20161223"></script>
	<%--<script type="text/javascript" src="${ctx}/static/js/sweet-alert.js"></script>--%>
	<%--<link rel="stylesheet" href="${ctx}/static/css/sweet-alert.css">--%>

	<script language="javascript">
		$(document).ready(function(){
			$('#botton_01').click(function(){
				//$(this).css('background','#FFBA01')
				$(this).animate({opacity:'0.6'},10);
				$(this).animate({opacity:'1'},10);
				//$(this).animate({backgroundColor:'#FFBA01'},100);
				//$(this).animate({backgroundColor:'#019BFF'},100);
			});
			$('#botton_02').click(function(){
				//$(this).css('background','#FFBA01')
				$(this).animate({opacity:'1'},10);
				$(this).animate({opacity:'0.6'},10);
				//$(this).animate({backgroundColor:'#FFBA01'},100);
				//$(this).animate({backgroundColor:'#019BFF'},100);
			});

		});
	</script>

</head>

<body>
	<header>
		<div class="menu_mc">
			<dl>
				<dd class="fh"></dd>
				<dt>估值</dt>
				<dd></dd>
			</dl>
		</div>
	</header>
	<form action="${ctx}/pinggu/result.html" enctype="application/x-www-form-urlencoded"  onsubmit="return checkForm(this);">
		<section class="mpT49">
			<div class="de_gzcs">
				<dl>
					<dt>
						<span class="spanName"></span>车辆型号
					</dt>
					<dd>
						<a id="model">选择车辆型号</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span class="spanTime"></span>上牌时间
					</dt>
					<dd>
						<a id="time" onclick="choiseDate()">选择上牌时间</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span class="spanCity"></span>所在城市
					</dt>
					<dd>
						<a id="city">选择城市</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span class="spanColor"></span>车辆颜色
					</dt>
					<dd>
						<a id="color">选择颜色</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span class="spanMileage"></span>行驶里程
					</dt>
					<dd class="de_xslc">
						<input type="number" name="mile" style="width: 100;" id="mile" placeholder="请输入(必填)" autocomplete="off" step="any"/>万公里
					</dd>
				</dl>
			</div>
		</section>
		<script>
			var de_gzcs_dd_width = $(document.body).width()-160;
			$('.de_gzcs dl dd').css("width",de_gzcs_dd_width);
		</script>
		<section class="de_gz_btn">
			<input id="botton_01" type="submit" value="快速估值">
			<input type="hidden" name="colorId" id="colorId" value="">
			<input type="hidden" name="colorName" id="colorName" value="">
			<input type="hidden" name="cityId" id="cityId" value="">
			<input type="hidden" name="cityName" id="cityName" value="">
			<input type="hidden" name="timeId" id="timeId" value="">
			<input type="hidden" name="timeName" id="timeName" value="">
			<input type="hidden" name="modelId" id="modelId" value="">
			<input type="hidden" name="modelName" id="modelName" value="">
		</section>
	</form>
    <section class="de_gz_btn01">
        <input id="botton_02" type="button" value="历史估值" onclick="location.href='${ctx}/pinggu/history'">
    </section>
	<%----%>

	<!--遮罩层-->
	<div id="zh"></div>
	<!--遮罩层-->
	<!--品牌页面-->
	<aside class="aside">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh"><a href="#" id="shut"><img src="${ctx}/static/images/header_fh.png" /></a></dd>
					<dt>选择车辆品牌</dt>
					<dd></dd>
				</dl>
			</div>
		</header>
		<div class="intro_fd_zm">
			<ul>

			</ul>
		</div>
		<section class="aside-main" id="categoryShow">
			<div class="intro_pp_rm">
				<ul>

				</ul>
			</div>
			<div id="content">

			</div>
		</section>
	</aside>
	<!--品牌页面-->
	<!--品牌车系页面-->
	<aside class="aside-Series">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh"><a href="javascript:;" id="shut-Series"><img src="${ctx}/static/images/header_fh.png" /></a></dd>
					<dt>选择车系</dt>
					<dd></dd>
				</dl>
			</div>
		</header>
		<section class="hbox">
			<div class="intro_pp_xzcx">
				<div class="row">
					<div class="L">
						<ul>

						</ul>
					</div>
					<div class="R">

					</div>
				</div>
			</div>
		</section>
	</aside>
	<!--品牌车系页面-->
	<!--品牌车系车型页面-->
	<aside class="aside-model">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh">
						<a href="javascript:;" id="shut-factory"><img
								src="${ctx}/static/images/header_fh.png"></a>
					</dd>
					<dt>选择车型</dt>
					<dd></dd>
				</dl>
			</div>
		</header>
		<section class="hbox">
			<div class="intro_pp_xzcx">
				<div class="row">
					<div class="L">
						<ul>

						</ul>
					</div>
					<div class="R">

					</div>
				</div>
			</div>
		</section>
	</aside>
	<!--品牌车系车型页面-->
	<!--上牌时间-->
	<aside class="aside-time">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh">
						<a href="javascript:void(0);" id="shut-time" ><img src="${ctx}/static/images/header_fh.png" /></a>
					</dd>
					<dt>选择上牌时间</dt>
					<dd></dd>
				</dl>
			</div>
		</header>

		<section class="aside-main">
			<div class="intro_xzsj_lst">
			</div>
		</section>
	</aside>
	<!--上牌时间-->
	<!--选择城市-->
	<aside class="aside-city">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh">
						<a href="javascript:void(0);" id="shut-city" ><img src="${ctx}/static/images/header_fh.png" /></a>
					</dd>
					<dt>选择地区</dt>
					<dd></dd>
				</dl>
			</div>
		</header>
		<section class="aside-main">
			<section class="mpT49"　>
				<div class="intro_fhys03 mt10">定位城市：</div>
			</section>

			<section>
				<div class="intro_fhys02 mt10">热门城市</div>
			</section>

			<section class="hot_city_local">
				<div class="intro_xzdq_lst">
					<ul>
						<li><span id="1" provinceId="2" provinceName="北京市">北京</span></li>
						<li><span id="73" provinceId="9" provinceName="上海市">上海</span></li>
						<li><span id="2" provinceId="2" provinceName="天津市">天津</span></li>
						<li><span id="257" provinceId="22" provinceName="重庆市">重庆</span></li>

					</ul>
				</div>
			</section>

			<!-- <section class="hideClass" style="display: none"> -->
			<section class="hideClass">
				<div class="intro_fhys02">历史选择</div>
			</section>

			<section class="hideClass">
				<div class="intro_xzdq_lst">
					<ul>
						<!-- 			<li><span id="12">北京</span></li>
                                    <li><span>上海</span></li>
                                    <li><span>合肥</span></li> -->
					</ul>
				</div>
			</section>

			<section>
				<div class="intro_fhys02">省份</div>
			</section>

			<section>
				<div class="intro_xzdq_sf_lst"></div>
			</section>
		</section>
	</aside>
	<!--选择城市-->
	<!--选择城市-->
	<aside class="aside-province">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh">
						<a href="javascript:void(0);" id="shut-province" ><img src="${ctx}/static/images/header_fh.png" /></a>
					</dd>
					<dt>选择地区</dt>
					<dd></dd>
				</dl>
			</div>
		</header>
		<section class="aside-main">
			<section>
				<div class="intro_xzdq_sf_lst">
					<ul>

					</ul>
				</div>
			</section>
		</section>
	</aside>
	<!--选择城市-->
	<!--选择颜色-->
	<aside class="aside-color">
		<header>
			<div class="menu_mc">
				<dl>
					<dd class="fh"><a href="javascript:void(0);" id="shut-color" ><img src="${ctx}/static/images/header_fh.png" /></a></dd>
					<dt>选择颜色</dt>
					<dd></dd>
				</dl>
			</div>
		</header>

		<section class="mpT49">
			<div class="intro_xzys_lst">
				<ul>
					<li><dl><dt><ol class="col01"></ol></dt><dd id="1">白色</dd></dl></li>
					<li><dl><dt><ol class="col02"></ol></dt><dd id="2">黑色</dd></dl></li>
					<li><dl><dt><ol class="col03"></ol></dt><dd id="3">咖啡色</dd></dl></li>
					<li><dl><dt><ol class="col04"></ol></dt><dd id="4">橙色</dd></dl></li>
					<li><dl><dt><ol class="col05"></ol></dt><dd id="5">灰色</dd></dl></li>
					<li><dl><dt><ol class="col06"></ol></dt><dd id="6">紫色</dd></dl></li>
					<li><dl><dt><ol class="col07"></ol></dt><dd id="7">红色</dd></dl></li>
					<li><dl><dt><ol class="col08"></ol></dt><dd id="8">绿色</dd></dl></li>
					<li><dl><dt><ol class="col09"></ol></dt><dd id="9">蓝色</dd></dl></li>
					<li><dl><dt><ol class="col10"></ol></dt><dd id="10">银色</dd></dl></li>
					<li><dl><dt><ol class="col11"></ol></dt><dd id="11">香槟色</dd></dl></li>
					<li><dl><dt><ol class="col12"></ol></dt><dd id="12">黄色</dd></dl></li>
					<li><dl><dt><ol class="col13"></ol></dt><dd id="13">多彩色</dd></dl></li>
					<li><dl><dt><ol class="col14">?</ol></dt><dd id="14">其他</dd></dl></li>
				</ul>
			</div>
		</section>
	</aside>
	<!--选择颜色-->

	<%----%>


	<div id="collect_zs" style="display:none;"></div>

    <script type="text/javascript">
		function choiseDate(){
			//var url = "${ctx}/static/popup/gz_date.html?"+Math.random();
			var modelId = getCookie('modelId');
			if(modelId==undefined || modelId == ""){
				alertMsg("请先选择车型");
			}else{
				//window.location.href = url;
			}
			return false;
		}

		if (getCookie('city')) {
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
        }
		
		$("#colorId").val(getCookie('colorId'));
		$("#colorName").val(getCookie('colorName'));
		$("#cityId").val(getCookie('cityId'));
		$("#cityName").val(getCookie('city'));
		$("#timeId").val(getCookie('timeId'));
		$("#timeName").val(getCookie('time'));
		$("#modelId").val(getCookie('modelId'));
		$("#modelName").val(getCookie('modelName'));

		function checkForm(frm) {
			//alertMsg(frm.cityId.value);
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
		}
		
		function alertMsg(msg){
			var _height = $(window).height();
			$("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
			$("#collect_zs").text(msg)
			$("#collect_zs").delay(500).show().delay(1000).fadeOut(1000);
		}
	</script>

<script type="text/javascript">

	$(function(){
		if($("#cityId").val()==""){
			//getZoneData();
		}
	});

//	function getZoneData(){
//		$.ajax({
//			url : "/api/getZone",
//			type : "GET",
//			dataType : 'json',
//			success : function(result) {
//				try{
//					if(result.status==1){
//						localCity(result.data);
//					}
//				}catch(e){
//					alertMsg("获取地区信息失败");
//				}
//			},
//			error : function(er) {
//				//BackErr(er);
//			}
//		});
//	}
	/*function getZoneData(){
		$.ajax({
			url:"http://192.168.188.82:8080/portal/api/zone/province",
			type : "get",
			dataType:'jsonp',
			jsonp:"callback",
			jsonpCallback:"success_jsonpCallback",
			success : function(result) {
				try{
					if(result.success==true){
						localCity(result.data,'.aside-city');
					}
				}catch(e){
					popupMsg.alertCar("获取地区信息失败");
				}
			},
			error : function(er) {
				//BackErr(er);
			}
		});
	}*/
//	function localCity(data){
//		var geolocation = new BMap.Geolocation();
//		var gc = new BMap.Geocoder();
//		geolocation.getCurrentPosition( function(r) {   //定位结果对象会传递给r变量
//					if(this.getStatus() == BMAP_STATUS_SUCCESS)
//					{  //通过Geolocation类的getStatus()可以判断是否成功定位。
//						var pt = r.point;
//						gc.getLocation(pt, function(rs){
//							var addComp = rs.addressComponents;
//							console.log(addComp.province +"|"+ addComp.city  +"|"+ addComp.district  +"|"+ addComp.street  +"|"+ addComp.streetNumber);
////							var data=zone;
//							var subData=null;
//							var flag=false;
//							for(var i=0;i<data.length;i++){
//								subData=data[i].subData;
//								if(subData!=null){
//									for(var j=0;j<subData.length;j++){
//										if(subData[j].zoneName.replace("市","")==addComp.city.replace("市","")){
//											var str = subData[j].zoneName;
//											setCookie("cityId",subData[j].zoneId);
//											setCookie("cityName",str);
//											$("#cityId").val(subData[j].zoneId);
//											$("#cityName").val(str);
//											$('#city').text(str);
//											flag=true;
//											break;
//										}
//									}
//								}
//								if(flag){
//									break;
//								}
//							}
//
//						});
//						$(".cityTip").css("display","none");
//					}
//					else
//					{
//						//关于状态码
//						//BMAP_STATUS_SUCCESS   检索成功。对应数值“0”。
//						//BMAP_STATUS_CITY_LIST 城市列表。对应数值“1”。
//						//BMAP_STATUS_UNKNOWN_LOCATION  位置结果未知。对应数值“2”。
//						//BMAP_STATUS_UNKNOWN_ROUTE 导航结果未知。对应数值“3”。
//						//BMAP_STATUS_INVALID_KEY   非法密钥。对应数值“4”。
//						//BMAP_STATUS_INVALID_REQUEST   非法请求。对应数值“5”。
//						//BMAP_STATUS_PERMISSION_DENIED 没有权限。对应数值“6”。(自 1.1 新增)
//						//BMAP_STATUS_SERVICE_UNAVAILABLE   服务不可用。对应数值“7”。(自 1.1 新增)
//						//BMAP_STATUS_TIMEOUT   超时。对应数值“8”。(自 1.1 新增)
//						switch( this.getStatus() )
//						{
//							case 2:
//								console.log( '位置结果未知 获取位置失败.' );
//								break;
//							case 3:
//								console.log( '导航结果未知 获取位置失败..' );
//								break;
//							case 4:
//								console.log( '非法密钥 获取位置失败.' );
//								break;
//							case 5:
//								console.log( '对不起,非法请求位置  获取位置失败.' );
//								break;
//							case 6:
//								console.log( '对不起,当前 没有权限 获取位置失败.' );
//								break;
//							case 7:
//								console.log( '对不起,服务不可用 获取位置失败.' );
//								break;
//							case 8:
//								console.log( '对不起,请求超时 获取位置失败.' );
//								break;
//
//						}
//					}
//
//				},
//				{enableHighAccuracy: true}
//		);
//	}
</script>
</body>
</html>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?fed9ca6874b32c074b1602d4e1a78656";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
