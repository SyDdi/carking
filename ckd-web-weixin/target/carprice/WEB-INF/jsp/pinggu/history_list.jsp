<%@ page import="com.car.mp.config.WechatConfig" %>
<%@ page import="me.chanjar.weixin.mp.api.WxMpService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<%
    WechatConfig wechatConfig = new WechatConfig();
    WxMpService wxMpService = wechatConfig.wxMpService();

%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
	<title>历史估值-行情价</title>
	<link href="${ctx}/static/css/css.css?20161020" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/iscroll-probe.js"></script>
</head>

<body>
<header>
	<div class="menu_mc">
		<dl>
            <dd class="fh"><a href="javascript:void(0);" onclick="history.back();"><img src="${ctx}/static/images/header_fh.png"></a></dd>
			<dt>历史估值</dt>
            <dd></dd>
		</dl>
	</div>
</header>

<section class="mpT49">
	<div class="intro_lsgz_lst">
		<c:forEach var="item" items="${list}" varStatus="status">
			<dl>
				<%--<dt><img src="${ctx}/static/images/intro_gzjg_001.jpg"></dt>--%>
				<dd onclick="doSearch('${item.colorId}','${item.colorName}','${item.cityId}','${item.cityName}','${item.timeId}','${item.timeName}','${item.modelId}','${item.modelName}','${item.mile}');">
					<h>${item.modelName}</h>
					<p>${item.cityName}/${item.timeName}上牌/${item.mile}万公里</p>
					<p><img src="${ctx}/static/images/intro_lsgz_01.png"><span><c:if test="${empty item.retailPrice}">-</c:if><c:if test="${!empty item.retailPrice}">${item.retailPrice}万</c:if></span>
                        <img src="${ctx}/static/images/intro_lsgz_02.png"><span><c:if test="${empty item.purchasePrice}">-</c:if><c:if test="${!empty item.purchasePrice}">${item.purchasePrice}万</c:if></span>
                        <span><fmt:formatDate value="${item.createTime}" pattern="yy-MM-dd"/></span></p>
				</dd>
			</dl>
		</c:forEach>
	</div>
</section>

<div style="display:none">
	<form action="result.html" method="post" id="frm">
		<input type="hidden" name="colorId" id="colorId" value="">
		<input type="hidden" name="colorName" id="colorName" value="">
		<input type="hidden" name="cityId" id="cityId" value="">
		<input type="hidden" name="cityName" id="cityName" value="">
		<input type="hidden" name="timeId" id="timeId" value="">
		<input type="hidden" name="timeName" id="timeName" value="">
		<input type="hidden" name="modelId" id="modelId" value="">
		<input type="hidden" name="modelName" id="modelName" value="">
		<input type="hidden" name="mile" id="mile" value="">
	</form>
</div>
<script type="text/javascript">
	function doSearch(colorId,colorName,cityId,cityName,timeId,timeName,modelId,modelName,mile){
		$("#colorId").val(""+colorId);
		$("#colorName").val(""+colorName);
		$("#cityId").val(""+cityId);
		$("#cityName").val(""+cityName);
		$("#timeId").val(""+timeId);
		$("#timeName").val(""+timeName);
		$("#modelId").val(""+modelId);
		$("#modelName").val(""+modelName);
		$("#mile").val(""+mile);

		$("#frm").submit();
	}
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
