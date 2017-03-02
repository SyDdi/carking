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
<title>选择车辆品牌-行情价</title>
<link href="${ctx}/static/css/css.css?20161020" rel="stylesheet"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
</head>

<body>
	<header>
		<div class="menu_mc">
			<dl>
				<dd class="fh">
					<a href="${ctx}/pinggu">关闭</a>
				</dd>
				<dt>选择车辆品牌</dt>
				<dd></dd>
			</dl>
		</div>
	</header>

	<div class="intro_fd_zm">
		<ul>
			<c:forEach var="item" items="${list}" varStatus="status">
				<li><a href="#intro_fhys01_${item.key}">${item.key}</a></li>
			</c:forEach>
		</ul>
	</div>
	<section class="mpT49" style="z-index:1; position: relative;">
		<div class="intro_pp_rm">
			<ul>
                <li><a href='factory_92_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst2.png"></dt><dd>奥迪</dd></dl></a></li>
                <li><a href='factory_90_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst13.png"></dt><dd>奔驰</dd></dl></a></li>
                <li><a href='factory_101_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst6.png"></dt><dd>宝马</dd></dl></a></li>
                <li><a href='factory_31_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst36.png"></dt><dd>丰田</dd></dl></a></li>
                <li><a href='factory_128_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst15.png"></dt><dd>本田</dd></dl></a></li>
				<li><a href='factory_80_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst30.png"></dt><dd>大众</dd></dl></a></li>
				<li><a href='factory_156_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst38.png"></dt><dd>福特</dd></dl></a></li>
				<li><a href='factory_51_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst17.png"></dt><dd>别克</dd></dl></a></li>
                <li><a href='factory_130_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst16.png"></dt><dd>标致</dd></dl></a></li>
                <li><a href='factory_151_0.html'><dl><dt><img src="${ctx}/static/images/intro_pp_lst121.png"></dt><dd>现代</dd></dl></a></li>
			</ul>
		</div>
	</section>
	<div id="content">
		<c:forEach var="item" items="${list}" varStatus="status">
            <section  id="intro_fhys01_${item.key}" style="padding-top:50px; margin-top:-50px; position:relative; z-index:-1;">
				<div class="intro_fhys01">${item.key}</div>
			</section>
			<c:forEach var="sub" items="${item.value}" varStatus="status2">
				<section>
					<div class="intro_pp_lst">
						<dl>
							<dt>
								<img src="${ctx}/static/images/intro_pp_lst${sub.pic_id }.png">
							</dt>
							<dd>
								<a href='factory_${sub.brand_id }_0.html'>${sub.brand_name_cn }</a>
							</dd>
						</dl>
					</div>
				</section>
			</c:forEach>
		</c:forEach>
	</div>
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
