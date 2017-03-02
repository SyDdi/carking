<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <title>估值结果-行情价</title>
    <link href="${ctx}/static/css/css.css?20161020" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
<header>
    <div class="menu_mc">
        <dl>
            <dd class="fh"><a href="#"><img src="${ctx}/static/images/header_fh.png"></a></dd>
            <dt>估值结果</dt>
            <dd></dd>
        </dl>
    </div>
</header>

<section class="mpT49">
    <div class="intro_lsgz_ycts"><ul><li><img src="${ctx}/static/images/intro_gth.png">该车型暂无信息</li></ul></div>
</section>

<%-- 没有关注的用户显示 二维码 --%>
<c:if test="${isShow == null or isShow =='' }">
    <section>
        <div class="intro_gzjg_gzh">
            <dl>
                <dt>请搜索并关注“行情价”微信公众号</dt>
                <dd><img src="${ctx}/static/images/intro_gzjg_003.jpg"></dd>
            </dl>
        </div>
    </section>
</c:if>
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