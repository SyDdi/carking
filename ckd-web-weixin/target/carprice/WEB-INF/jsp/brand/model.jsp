<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>选择车型-行情价</title>
<link href="${ctx}/static/css/css.css?20161020" rel="stylesheet"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
</head>

<body>
	<header>
		<div class="menu_mc">
			<dl>
				<dd class="fh">
					<a href="${ctx}/brand/factory_${brandId}_0.html"><img
						src="${ctx}/static/images/header_fh.png"></a>
				</dd>
				<dt>选择车型</dt>
				<dd></dd>
			</dl>
		</div>
	</header>
	<section class="hbox">
		<div class="intro_pp_xzcxn">
			<div class="row">
				<div class="L">
					<ul>
						<c:forEach var="item" items="${yearData}" varStatus="status">
							<c:choose>
								<c:when test="${year == item}">
									<li class="on">
								</c:when>
								<c:otherwise>
									<li>
								</c:otherwise>
							</c:choose>
							<a
								href="${ctx}/brand/model_${brandId}_${factoryId}_${seriesId}_${item}.html">${item}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="R">
					<ul>
						<c:if test="${not empty modelData}">
							<c:forEach var="item" items="${modelData}" varStatus="status">
								<li class="modelli"><dl>
										<dt id="${brandId}_${factoryId}_${item.series.seriesName}_${item.series.seriesIds}_${year}_${item.modelId}">${item.series.seriesName} ${item.modelName}</dt>
									</dl></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		$('.modelli').bind('click', function(e) {
			window.location.href = "${ctx}/pinggu?"+Math.random();
			var text = "${year}款 ${brand.brand_name_cn} "+$(this).find("dt").text();
			var value = $(this).find("dt").attr("id");
			removeCookie('modelName');
			removeCookie('modelId');

			setCookie('modelName', text);
			setCookie('modelId',value);
		});

        function removeCookie(name,value) {
			$.removeCookie(name);
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
