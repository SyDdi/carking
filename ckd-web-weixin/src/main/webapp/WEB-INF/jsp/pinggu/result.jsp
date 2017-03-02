<%@ page language="java" import="com.car.mp.domain.ResultEntity" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	ResultEntity result = (ResultEntity)request.getAttribute("result");
	String zdPrice = (result==null)?"":result.getNewPriceW();//厂商指导价，没有的情况下显示暂无估计
	String newPrice = (result==null)?"":result.getNewcarUpdTaxPrice();;//新车实际成交价

	//暂无估价时，价格趋势图自动不显示
	//暂无估价时，价格分布图显示“该车型暂无估价”（“立即卖车”按钮仍保留）
    String retailPrice = (result==null)?"":result.getRetailPrice();
	boolean hasZdPrice = (StringUtils.isNotBlank(retailPrice));

	zdPrice = (StringUtils.isNotBlank(zdPrice))?(zdPrice+"万"):"-";
	newPrice = (StringUtils.isNotBlank(newPrice))?(newPrice+"万"):"-";
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
	<title>估值报告-行情价</title>
	<link href="${ctx}/static/css/css.css?20161020" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
	<%
		//只当有估价的时候显示价格趋势，所以不显示价格趋势的话，用不到该js
		if(hasZdPrice){
	%>
	<script type="text/javascript" src="${ctx}/static/js/ichart.latest.js?v=2.6"></script>
	<script type="text/javascript" src="${ctx}/static/js/appraiseInfo.js?v=1.9"></script>
	<script type="text/javascript">
		var objArray = JSON.parse('${ageAppraise.priceList}');
		var retailArray=[];
		var purchaseArray = [];
		var labels=[];
		var max = 0;
		var min = 100000;
		for(var i=0;i<objArray.length;i++){
			retailArray.push(parseFloat(objArray[i].retailPrice));
			purchaseArray.push(parseFloat(objArray[i].purchasePrice));
			labels.push(objArray[i].carAge.substring(2,7).replace("-","/"));
			max = Math.max(max,parseFloat(objArray[i].retailPrice),parseFloat(objArray[i].purchasePrice));
			min = Math.min(min,parseFloat(objArray[i].retailPrice),parseFloat(objArray[i].purchasePrice));
		}
		var startScale = Math.floor(min*0.9);
		var endScale = Math.round(max*1.1,1);
        var spaceScale  =Math.round((endScale-startScale)/5,1);
        endScale = startScale+spaceScale*5;
        if(max<1) {
            startScale = 0;
            endScale = 1;
            spaceScale = 0.2;
        }
        //console.log(startScale+","+endScale+","+spaceScale);
		$(function(){
			createChat(retailArray,purchaseArray,labels,startScale,endScale,spaceScale);
			
			//车龄赋值
			var nowTime=new Date();
			var licenseTime=new Date("${search.timeId}");
			var yearInterval=nowTime.getFullYear()-licenseTime.getFullYear();
			var monthInterval=nowTime.getMonth()-licenseTime.getMonth()+1;
			var year=parseInt((yearInterval*12+monthInterval)/12);
			var month=(yearInterval*12+monthInterval)%12;
			$("#vehicleAge").html(year!=0&&month!=0?year+"年"+month+"个月":month==0?year+"年":month+"个月");
		});
		function condClick(cond){
			var uri="${ctx}/pinggu/calc.html?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&cond="+cond;
			$("#calculatorRedirect").attr("href",uri);
			var condChinese="";
			var priceDifference="";
			var purchasePrice="";
			if(cond=="better"){
				condChinese="优秀";
				priceDifference="${result.betterPurchasePriceDifference>0?'+':null}${result.betterPurchasePriceDifference==0?'-':result.betterPurchasePriceDifference}${result.betterPurchasePriceDifference!=0?'元':null}"
				purchasePrice="${result.betterPurchasePrice}";
			}else if(cond=="worse"){
				condChinese="一般";
				priceDifference="${result.worsePurchasePriceDifference>0?'+':null}${result.worsePurchasePriceDifference==0?'-':result.worsePurchasePriceDifference}${result.worsePurchasePriceDifference!=0?'元':null}";
				purchasePrice="${result.worsePurchasePrice}";
			}else{
				condChinese="良好";
				priceDifference="${result.purchasePriceDifference>0?'+':null}${result.purchasePriceDifference==0?'-':result.purchasePriceDifference}${result.purchasePriceDifference!=0?'元':null}";
				purchasePrice="${result.purchasePrice}";
			}
			$("#cond").html(condChinese);
			$("#condPrice").html(priceDifference);
			$("#purchasePriceInTable").html(purchasePrice+"万元");
		}
	</script>
	<%
		}
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
<header>
	<div class="menu_mc">
		<dl>
			<dd class="fh"><a href="javascript:void(false);history.back();"><img src="${ctx}/static/images/header_fh.png"></a></dd>
			<dt>估值报告</dt>
			<dd></dd>
		</dl>
	</div>
</header>

<section class="mpT49">
	<div class="intro_gzjg_ks">
		<dl>
			<dt><img src="${ctx}/static/images/intro_pp_lst${brand.pic_id }.png"></dt>
			<dd>
				<h>${search.modelName}</h>
				<p>厂商指导价：<span><%=zdPrice%></span></p>
				<p>新车含税价：<span><%=newPrice%></span></p>
				<p>易卖度：<c:forEach var="i" begin="1" end="${6-result.easyToSell}"><img src="${ctx}/static/images/star01.png" align="absmiddle"></c:forEach><c:forEach var="i" begin="1" end="${5-(6-result.easyToSell)}"><img src="${ctx}/static/images/star02.png" align="absmiddle"></c:forEach></p>
			</dd>
		</dl>
	</div>
</section>

<section>
	<div class="intro_gzjg_fl">
		<ul>
			<li><dl><dt>所在城市<!--img src="${ctx}/static/images/intro_jt_xx.png"--></dt><dd>${search.cityName}</dd></dl></li>
			<li class="Lsx"><dl><dt>上牌日期<!--img src="${ctx}/static/images/intro_jt_xx.png"--></dt><dd>${search.timeName}</dd></dl></li>
			<li class="Lsx"><dl><dt>行驶里程</dt><dd><!--input type="text" value="${search.mile}"-->${search.mile}万公里</dd></dl></li>
			<li class="Lsx"><dl><dt>车身颜色<!--img src="${ctx}/static/images/intro_jt_xx.png"--></dt><dd>${search.colorName}</dd></dl></li>

		</ul>
	</div>
</section>

<section class="mt10">
	<div class="intro_fhys01">估值结果</div>
</section>

<section>
	<div class="intro_qhys01">
		<ul>
            <li onclick="condClick('worse')">车况一般</li>
			<li onclick="condClick('ordinary')" class="on">车况良好</li>
			<li onclick="condClick('better')">车况优秀</li>
		</ul>
	</div>
	<div class="intro_gzbb_3jg">
		<ul>
			<li>车商收购价</li>
			<li class="L_line">个人交易价</li>
			<li class="L_line">车商零售价</li>
		</ul>
	</div>
	<%
//只当有估价的时候显示,否则显示暂无估价
if(hasZdPrice){
	%>
	<div class="intro_gzbb_xjt">
		<ul>
			<li><img src="${ctx}/static/images/intro_gzbb_xjt01.png" align="top"></li>
			<li><img src="${ctx}/static/images/intro_gzbb_xjt01.png" align="top"></li>
			<li><img src="${ctx}/static/images/intro_gzbb_xjt01.png" align="top"></li>
		</ul>
		
        <ul style="display: none">
            <li>${result.worsePurchasePrice}万
            <!-- 
                <span class="dotA"><a href="${ctx}/pinggu/calc.html?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&cond=worse">车价分析</a></span>
             -->
            </li>
            <li>${result.worsePersonalTradingPrice }万</li>
            <li>${result.worseRetailPrice}万</li>
        </ul>
		<ul>
            <li>${result.purchasePrice}万
            <!-- 
                <span class="dotA"><a href="${ctx}/pinggu/calc.html?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&cond=ordinary">车价分析</a></span>
             -->
            </li>
            <li>${result.personalTradingPrice }万</li>
            <li>${result.retailPrice}万</li>
		</ul>
        <ul style="display: none">
            <li>${result.betterPurchasePrice}万
            <!--
                <span class="dotA"><a href="${ctx}/pinggu/calc.html?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&cond=better">车价分析</a></span>
            -->
            </li>
            <li>${result.betterPersonalTradingPrice }万</li>
            <li>${result.betterRetailPrice}万</li>
        </ul>
	</div>
	
  <!-- 新增修改 -->
  <div class="intro_gzbb_djsm">
   <ul><li><img src="${ctx}/static/images/intro_gzbb_sjt01.png" align="top"></li><li></li><li></li></ul>
  </div>
   
  <div class="intro_gzbb_djssgj">
   <ul><li class="t1">定价说明</li><li></li><li><a id="calculatorRedirect" href="${ctx}/pinggu/calc.html?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&cond=ordinary">定价工具<span><img src="${ctx}/static/images/xz_jt01.png" align="top"></span></a></li></ul>
  </div>
  <div class="intro_gzbb_djsmsl">以车商收购价<span id="purchasePriceInTable">${result.purchasePrice}万元</span>为例</div>
  <div class="intro_gzbb_djssxx">
   <ul>
    <li><dl><dd>车龄</dd><dd class="t1" id="vehicleAge"></dd><dt><span>${result.basePrice}万元</span><br>据车型保值率所得基准价</dt></dl></li>
	<li><dl><dd>行驶里程</dd><dd class="t1 t2">${search.mile}<br>万公里</dd><dt><span>${result.milePrice>0?"+":null}${result.milePrice==0?"-":result.milePrice}${result.milePrice!=0?"元":null}</span><br>行驶里程${result.milePrice>0?"相对较少":result.milePrice<0?"相对较多":result.milePrice==0?"正常":null}</dt></dl></li>
	<li><dl><dd>车身颜色</dd><dd class="t1">${search.colorName}</dd><dt><span>${result.colorPrice>0?"+":null}${result.colorPrice==0?"-":result.colorPrice}${result.colorPrice!=0?"元":null}</span><br>根据该车颜色的热销程度</dt></dl></li>
	<li><dl><dd>所在城市</dd><dd class="t1">${search.cityName}</dd><dt><span>${result.zonePrice>0?"+":null}${result.zonePrice==0?"-":result.zonePrice}${result.zonePrice!=0?"元":null}</span><br>该车型在${search.cityName}${result.zonePrice>0?"需求较旺":result.zonePrice<0?"需求较淡":result.zonePrice==0?"供需平衡":null}</dt></dl></li>
	<li><dl><dd>车况</dd><dd class="t1" id="cond">良好</dd><dt><span id="condPrice">-</span><br>根据车况</dt></dl></li>
   </ul>
  </div>
  <!-- 新增修改 -->
	<%
		}else{
	%>
	<div class="intro_gzbb_zwgj">
		<ul>
			<li><img src="${ctx}/static/images/intro_gth.png" align="absmiddle">该车型暂无估价</li>
		</ul>
	</div>
	<%
		}
	%>
	
</section>

<section class="fc_bottom01">
	<dl>
		<dd></dd>
		<dt>
			<%-- <a href="${ctx}/static/hotline.html"><div class="intro_mcfwrx_btn">交易咨询</div></a> --%>
			<a href="${ctx}/transfer?modelId=${search.modelId}&timeId=${search.timeId}&colorId=${search.colorId}&mile=${search.mile}&zoneId=${search.cityId}&purchasePrice=${result.personalTradingPrice}"><div class="intro_mcfwrx_btn01">获取实际报价</div></a>
		</dt>
		<dd><a href="#" id="wh_btn">?</a></dd>
	</dl>
</section>

<div class="fc_zsgj_wh">
	<ul>
		<li>
			<dl>
				<dt>更加给力的报价</dt>
				<dd>
					本页显示的价格是由行情价人工智能系统计算输出。<br><br>
					我们还可以帮助您便捷获得多家二手车平台商甚至个人的报价信息，只需填写基本车辆信息并提交，就可以获得真实、可成交的价格。<br><br>
					<p>不必到处询价，二手车价格，竟然可以如此透明！</p>
				</dd>
				<dd><span><img src="${ctx}/static/images/zsgj_gb.png" id="zsgj_gb"></span></dd>
			</dl>
		</li>
	</ul>
</div>

<%
	//只当有估价的时候显示,否则不显示价格趋势
	if(hasZdPrice){
%>
<section class="mt10">
	<div class="intro_fhys01">价格趋势</div>
</section>

<section>
	<div class="intro_gzbg_jgqs" id='canvasDiv'></div>
</section>



<%
	}
%>

<%-- 没有关注的用户显示 二维码 --%>
<c:if test="${isShow == null or isShow =='' }">

	<section>
		<div style=" border-top:1px solid #EDEDED;"></div>
		<div class="intro_gzjg_gzh">
			<dl>
				<dt>请搜索并关注“行情价”微信公众号</dt>
				<dd><img src="${ctx}/static/images/intro_gzjg_003.jpg"></dd>
			</dl>
		</div>
	</section>
</c:if>

<div style="float:left; width:100%; height:61px;"></div>
</body>
</html>
<script>


	$(document).ready(function(){
		$("#wh_btn").click(function(){
			$(".fc_zsgj_wh").fadeIn("slow");
		});
		$("#zsgj_gb").click(function(){
			$(".fc_zsgj_wh").fadeOut("slow");
		});
	});

	$('.fc_zsgj_wh').bind("touchmove",function(e){
		e.preventDefault();
	});


	var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?fed9ca6874b32c074b1602d4e1a78656";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
