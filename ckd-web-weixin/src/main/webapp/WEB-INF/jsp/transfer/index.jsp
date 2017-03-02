<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>
		<c:if test="${model != null and model !='' }">
			${model.carYear}款 ${model.brand} ${model.familyGroupName} ${model.family} ${model.shortName}
		</c:if>
	</title>
	<link href="${ctx}/static/css/css.css?time=20170109" rel="stylesheet" type="text/css">
	<%--<link href="${ctx}/static/css/sweet.css?time=20161207" rel="stylesheet" type="text/css"/>--%>
	<%@ include file="/WEB-INF/jsp/common/config.jsp"%>
	<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/getscript.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<%--<script type="text/javascript" src="${ctx}/static/js/sweet-alert.js"></script>--%>
	<script type="text/javascript" src="${ctx}/static/js/localResizeIMG-4.9.35/dist/lrz.bundle.js" ></script>
	<script type="text/javascript" src="${ctx}/static/js/zepto.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/pinpai.js?time=20161223"></script>
	<script type="text/javascript" src="${ctx}/static/js/time.js?time=20161223"></script>
	<script type="text/javascript" src="${ctx}/static/js/city.js?time=20161223"></script>
	<script type="text/javascript" src="${ctx}/static/js/weixin/weixinToken.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common/check_number.js"></script>
</head>
<script>
	var userInfo = weixin.getUserInfo();//验证是否登录微信
//	var userInfo ;
</script>
<section>
<header>
 <div class="menu_mc">
  <dl>
   <dd class="fh"><a href="javascript:void(false);history.back();"><img src="${ctx}/static/images/header_fh.png"></a></dd>
   <dt>基本信息</dt>
   <dd></dd>
  </dl>
 </div>
</header>

<!-- 基本信息表单提交 -->
<form id="saveForm" action="${ctx}/transfer/generate" onsubmit="return submitForm()"  method="post">
<section class="mpT49">
 <div class="basic_inf_01">
  <ul>
   <li id="model">
  		<dl>
  			<dt>车辆型号：</dt>
  			<dd>
				<c:if test="${model != null and model !='' }">
					${model.carYear}款 ${model.brand} ${model.familyGroupName} ${model.family} ${model.shortName}
				</c:if>
				<c:if test="${model == null or model =='' }">
			  	请选择车型
				</c:if>
  			</dd>
  		</dl>
   
<%-- 	   <c:if test="${model != null and model !='' }">
		   ${model.carYear}款 ${model.brand} ${model.family} ${model.shortName}
	   </c:if>
	   <c:if test="${model == null or model =='' }">
		  请选择车型
	   </c:if> --%>
   </li>
   <li id="city"><dl><dt>所在城市：</dt><dd>${zoneName}</dd></dl></li>
   <li id="time"><dl><dt>上牌日期：</dt><dd>${timeId}</dd></dl></li>
   <li class="nbg"><dl><dt>行驶里程：</dt><dd><input id="mileage" name="miles" type="number" value="${mile}" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')">万公里</dd></dl></li>
   <li id="color"><dl><dt>车身颜色：</dt><dd>${outerColor}</dd></dl></li>
  </ul>
 </div>
</section>
<section>
 <div class="basic_inf_02">
  <ul>
   <li><dl><dt class="carInput">预期售价：</dt><dd><input type="number" name="purchasePrice" placeholder="请输入(必填)" value="${purchasePrice}" onkeyup="clearNoNum(this);"><span>万元</span></dd></dl></li>
   <li id="paizhao" class="area"><dl><dt class="carInput">牌照属地：</dt><dd>${regZone==null?"请选择":regZone}</dd></dl></li>
   <li><dl><dt style="color: #303030;">更多说明：</dt><dd><textarea name="info" cols="" rows="">${vehicle.info==null?"这辆爱车已陪伴我数年，平时主要上下班代步用，车子保养得也不错。现因个人原因出售该车，感兴趣的朋友，请联系我！":vehicle.info}</textarea></dd></dl></li>
   <li><dl><dt class="carInput">　车牌号：</dt><dd><input name="numberPlate" type="text" id="chep" value="${vehicle.numberPlate}" placeholder="选填" style="color:#BABABA;"  onfocus="if(value=='选填'){this.style.color='#019BFF';value=''}"></dd></dl></li>
   <li><p class="basicP">我们承诺会对您的车牌号进行保密。</p></li>
	  <li><div class="suggest alert-success">隐私提示：程序会自动对上传图片中的车牌号码进行马赛克处理。如果失败，请您另行处理。</div></li>
	  <c:if test="${vehicle.id != null and vehicle.id !='' }">
		  <li><dl><dt class="carInput">手机号码：</dt><dd><input type="number" name="phoneNumber" value="${vehicle.telephone}"></dd></dl></li>
		  <li><p style="line-height:20px; text-align:left; padding-top:5px;">留下手机号，会有专业人员与您核实车况，以提供更标准的车况描述。我们承诺，未经您的同意，我们不会将您的手机号透露给第三方。</p></li>
	  </c:if>
  </ul>
	<input type="hidden" id="userId" name="userId" value="${userId}">
	<input type="hidden" id="id" name="id" value="${vehicle.id}">
	<input type="hidden" id="modelId" name="modelId" value="${model.modelId}">
	<input type="hidden" id="colorId" name="colorId" value="${colorId}">
	<input type="hidden" id="colorName" name="outerColor" value="${outerColor}">
	<input type="hidden" id="timeId" name="regDate" value="${timeId}">
	<input type="hidden" id="cityId" name="zoneId" value="${vehicle.zoneId}">
	<input type="hidden" id="provinceId" name="provinceId" value="${vehicle.provinceId}">
	<input type="hidden" id="regZoneId" name="regZoneId" value="${vehicle.regZoneId}">
	<input type="hidden" id="regProvinceId" name="regProvinceId" value="${vehicle.regProvinceId}">
	<input type="hidden" id="picpath_1" name="picpath1" picType="1" value="${picpath1}">
	<input type="hidden" id="picpath_2" name="picpath2" picType="2" value="${picpath2}">
	<input type="hidden" id="picpath_3" name="picpath3" picType="3" value="${picpath3}">
	<input type="hidden" id="picpath_4" name="picpath4" picType="4" value="${picpath4}">
	<input type="hidden" id="picpath_5" name="picpath5" picType="5" value="${picpath5}">
	<input type="hidden" id="picpath_6" name="picpath6" picType="6" value="${picpath6}">
 </div>


</section>
</form>

<section>
<form>

	<div id="collect_zs" style="display:none;"></div>
	<div class="basic_inf_03">
	<ul>
	    <li>

		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="1" />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath1" picType="1"  >
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx01.jpg" />
		   			<img class="img3" id="pic1"  src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   			<%--<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>左前</dd>
		   	</dl>
	    </li>
	    <li>
		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="2"  />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath2"picType="2"  >
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx02.jpg" />
		   			<img class="img3" id="pic2"  src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   		<%--	<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>右后</dd>
		   	</dl>
	    </li>
	    <li>
		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="3"  />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath3" picType="3" >
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx03.jpg" />
		   			<img class="img3" id="pic3"  src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   			<%--<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>仪表盘特写</dd>
		   	</dl>
	    </li>
	    <li>
		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="4"  />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath4" picType="4" >
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx04.jpg" />
		   			<img class="img3" id="pic4"  src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   			<%--<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>仪表盘全景</dd>
		   	</dl>
	    </li>
	    <li>
		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="5"  />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath5" picType="5" >
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx05.jpg" />
		   			<img class="img3" id="pic5" src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   			<%--<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>后排座椅</dd>
		   	</dl>
	    </li>
	    <li>
		   	<input type="file" class="filepath" accept="image/*" name="logo" data-index="6"  />
		   	<div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
			<input type="hidden" id="picpath6" picType="6">
			<dl>
		   		<dt class="img_f">
		   			<img class="img2" src="${ctx}/static/images/intro_basic_ncx06.jpg" />
		   			<img class="img3" id="pic6" src="" style="display:none;" />
					<img class="loading" src="${ctx}/static/images/loading.gif"/>
		   			<%--<a href="javascript:;">
		   				<img class="img1" src="${ctx}/static/images/intro_basic_jh.png"><span>添加图片</span>
		   			</a>--%>
		   		</dt>
		   		<dd>前排座椅</dd>
		   	</dl>
	    </li>
	</ul>
 </div>
</form>
</section>

</section>

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
		
		<!--选择牌照属地-->
		<aside class="aside-license">
			<header>
				<div class="menu_mc">
					<dl>
						<dd class="fh">
							<a href="javascript:void(0);" id="shut-license"><img src="${ctx}/static/images/header_fh.png" /></a>
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
<!-- 						<li><span id="1">北京</span></li>
						<li><span id="73">上海</span></li>
		                <li><span id="2">天津</span></li>
						<li><span id="257">重庆</span></li> -->
						
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
				<!-- <li><span id="1" onclick="historyClicklicense(1,'北京',2,'北京市')">北京</span><li>
				<li><span id="73" onclick="historyClicklicense(73,'上海',9,'上海市')">上海</span><li>
				<li><span id="2" onclick="historyClicklicense(2,'天津',2,'天津市')">天津</span><li>
				<li><span id="257" onclick="historyClicklicense(257,'重庆',22,'重庆市')">重庆</span><li> -->
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
		<!--选择牌照属地-->
		<!--选择牌照属地-->
		<aside class="aside-license-region">
			<header>
				<div class="menu_mc">
					<dl>
						<dd class="fh">
							<a href="javascript:void(0);" id="shut-license-region"><img src="${ctx}/static/images/header_fh.png" /></a>
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
		<!--选择牌照属地-->
<section>
<div class="basic_inf_btn" onclick="save();">
	<c:if test="${vehicle.id != null and vehicle.id !='' }">
		保  存
	</c:if>
	<c:if test="${vehicle.id == null or vehicle.id =='' }">
		提　交
	</c:if>
</div>
</section>
<script type="text/javascript">
	function save(){
			//console.info(123);
			$("#saveForm").submit();
	}

	function submitForm(){
		//console.info($("#saveForm").serialize());
		var pp=$("input[name='modelId']").val();
		if(pp==null||pp==""){
			alertMsg("请选择车型");
 		return false;
		}
		var pp=$("input[name='zoneId']").val();
		if(pp==null||pp==""){
			alertMsg("请选择所在地区");
 		return false;
		}
		var pp=$("input[name='regDate']").val();
		if(pp==null||pp==""||pp=="请选择"){
			alertMsg("请选择上牌日期");
 		return false;
		}
		var pp=$("input[name='miles']").val();
		if(pp==null||pp==""){
			alertMsg("请填写行驶里程");
 		return false;
		}
		var pp=$("input[name='colorId']").val();
		if(pp==null||pp==""){
			alertMsg("请选择车身颜色");
 		return false;
		}
		var pp=$("input[name='purchasePrice']").val();
		if(pp==null||pp==""){
			alertMsg("请填写预期售价");
 		return false;
		}
		var pp=$("input[name='regZoneId']").val();
		var qq=$("input[name='regProvinceId']").val();
		if(pp==null||pp==""||qq==null||qq==""){
			alertMsg("请选择牌照属地");
 		return false;
		}
		var pp=$("textarea[name='info']").val();
		if(pp==null||pp==""){
			alertMsg("请填写更多说明");
 		return false;
		}
		if($("input[name='numberPlate']").val() != null && $("input[name='numberPlate']").val() != "") {
			var numberPlate = isCarNumber($("input[name='numberPlate']").val());
			if (!numberPlate) {
				alertMsg("车牌号格式不正确,只能为中文开头7位数值");
				return false;
			}
		}
	}
	$(function(){
		/*进入基本信息的时牌照属地的显示*/
		if($('#regZoneId').val()==''){
			$('#regZoneId').val('${vehicle.zoneId}');
			$('#regProvinceId').val('${vehicle.provinceId}');
			$('#paizhao dd').text('${zoneName}');
		}

		/*车牌号验证*/
		$("input[name='numberPlate']").blur(function(){
			var car = $(this).val();
			if(car!=null && car != "") {
				var result = isCarNumber(car);
				if (result) {
					$(this).val(car.toUpperCase());
					return true;
				} else {
					alertMsg("车牌号格式不正确,只能为中文开头7位数值");
					return false;
				}
			}
		});
		

		/*计算6张图片的高度*/
		$(".basic_inf_03 dt img").on("load",function(){
			var basic_pic_height = $(".basic_inf_03 dt img:first").height();
			$(".basic_inf_03 dt").css("height",basic_pic_height);
		});

		var basic_pic_height = $(".basic_inf_03 dt img:first").height();
		$(".basic_inf_03 dt").css("height",basic_pic_height);
		/*计算6张图片的高度 end*/

		if(userInfo != null) {
			$("#userId").val(userInfo.id);
		}
		editImg();
	  /* $('.filepath').on('change', function(){
	   			var _this = $(this);
			   $(this).siblings('dl').find(".img2").hide();
			   $(this).siblings('dl').find('dt').append('<img class="loading" src="${ctx}/static/images/loading.gif"/>');
	             lrz(this.files[0], {width: 640,quality: 0.8,})
	                .then(function (rst) {
	                	var imgSrc = rst.base64;
	                	_this.siblings('dl').find(".img3").attr('src',imgSrc);
	                	_this.next('.close').show();
	                //	_this.siblings('dl').find(".img2").hide();
						if(_this.siblings('dl').find(".img3").show()){
							$('.loading').hide();
						};
	                	_this.siblings('dl').find(".img3").show();
	                	_this.siblings('dl').find(".img3").css({'opacity':'1','position':'absolute','width':'100%','height':'100%'})
						var _height = $(window).height();
						$("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
						$("#collect_zs").text("图片上传中...");
						$("#collect_zs").delay(500).show();
	                    $.ajax({
	                        url: '${portal}/upload/base64',
	                        type: 'POST',
	                        data:{data:rst.base64},
	                        crossDomain: true,
	                        dataType: 'json',
	         				//jsonp: 'callback',
	         				//jsonpCallback:"success_jsonpCallback",
	                        success: function (response) {
								$("#picpath_"+_this.data("index")).val(response.data.filePath);
								$("#collect_zs").text("图片上传完成！");
								$("#collect_zs").fadeOut(1000);
	                        },
	                        error: function (jqXHR, textStatus, errorThrown) {

	                            if (textStatus == 'timeout') {
	                                a_info_alert('请求超时');
									$("#collect_zs").text("请求超时！");
									$("#collect_zs").fadeOut(1000);
	                                return false;
	                            }
	                        }
	                    });

	                })
	                .catch(function (err) {

	                })
	                .always(function () {

	                });

	    });

        $('.close').bind('click',function(){
        	$(this).hide();
	        $(this).siblings('dl').find(".img2").show();
	        $(this).siblings('dl').find(".img3").hide();
			var picId = $(this).siblings('dl').find(".img3").attr("id");
			var value = picId.replace(/[^0-9]/ig,"");
			$("#picpath_"+value).val("");
        })
	});*/
		/*隐藏车牌号输入*/
		$('#chep').parent().parent().hide();
		$('.basicP').hide();

		/*上传图片*/
		$('.filepath').on('change', function(){
			var _this = $(this);
			_this.siblings('dl').find(".img2").hide();
			//_this.siblings('dl').find('dt').append('<img class="loading" src="images/loading.gif"/>');
			var thisIndex = _this.data('index');
			var _this2 = _this[0],
					_file = _this2.files[0];
			/*重新绑定change事件*/
			changeImg(_file,_this);
		})

		/*点击删除按钮显示默认图片*/
		$('.basic_inf_03 .close').on('click',function(){
			$(this).hide();
			$(this).siblings('dl').find(".img2").show();
			$(this).siblings('dl').find(".img3").hide();
			$(this).siblings('dl').find(".img3").attr('src','');
			$(this).siblings('dl').remove('.loading');
			var picId = $(this).siblings('dl').find(".img3").attr("id");
			var value = picId.replace(/[^0-9]/ig,"");
			$("#picpath_"+value).val("");

		})



	function editImg(){
		if("${vehicle.id}" == null || "${vehicle.id}" == ""){
			return ;
		}
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/pictures?vhclId=${vehicle.id}",
			dataType:"json",
			success:function(data){
//				console.info(data)
				var endData=data.data;
				for(var i in endData){
//					$(".img2").hide();
					var type = endData[i].type;
					var imgSrc = "${imgPath}/"+endData[i].img;
					if(endData[i].img != null && endData[i].img != "") {
						var _parent = $("#pic" + type).parent().parent().parent();
						_parent.find('dl').find(".img3").attr('src', imgSrc);
						_parent.find('.close').show();
						_parent.find('dl').find(".img1").hide();
						_parent.find('dl').find(".img2").hide();
						_parent.find('dl').find("span").hide();
						_parent.find('dl').find(".img3").show();
						_parent.find('dl').find(".img3").css({
							'opacity': '1',
							'position': 'absolute',
							'width': '100%',
							'height': '100%'
						});
					}
				}
			}
		});
	}
		/*重新绑定change事件*/
		function changeImg(_file,_this){

			var thisIndex = _this.data('index');
			_this.siblings('dl').find(".img2").hide();
			_this.siblings('dl').find(".img3").hide();
			_this.siblings('dl').find('.loading').show();
			_this.siblings('dl').find(".img3").attr('src','');
			lrz(_file, {width: 1400,quality: 1.0,}).then(
				function (rst) {
					var imgSrc = rst.base64;

					$.ajax({
						url:'${portal}/upload/base64',
						type: 'POST',
						data:{data:imgSrc},
						crossDomain: true,
						dataType: 'json',
//						jsonp: 'callback',
//						jsonpCallback:"success_jsonpCallback",
						success: function (response) {
							console.info(response);
							_this.next('.close').show();
							_this.siblings('dl').find(".img3").attr('src','${imgPath}'+response.data.filePath);
							_this.siblings('dl').find(".img3").show();
							_this.siblings('dl').find(".img3").css({'opacity':'1','position':'absolute','width':'100%','height':'100%'})
							if(_this.siblings('dl').find(".img3").show()){
								$('.loading').hide();
							};
							/*把图片地址存到表单中s*/
							$("#picpath_"+_this.data("index")).val(response.data.filePath);
							$("#collect_img").delay(500).show().delay(1000).fadeOut(1000);

							/*把旧的change事件替换成新的*/
							$('<input type="file" class="filepath" capture="camera" accept="image/*" name="logo" data-index="'+ thisIndex +'"/>').replaceAll($('.filepath')[thisIndex-1]);
							/*off解决两次调用change事件*/
							$('.filepath').off('change').on('change', function(){
								var _this = $(this)
								var _this2 = _this[0],
										_file = _this2.files[0];
								changeImg(_file,_this);
							})
						},
						complete: function(xmlHttpRequest) {
							//console.info($('.filepath')[thisIndex-1]);


						},
						error: function (jqXHR, textStatus, errorThrown) {

							if (textStatus == 'timeout') {
								a_info_alert('请求超时');
								return false;
							}
						}
					});
				}).catch(function (err) {

				}).always(function () {

				});
		}
	});
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