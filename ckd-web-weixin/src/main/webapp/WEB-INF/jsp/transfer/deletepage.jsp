<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">
    <link href="${ctx}/static/css/css.css?time=20161220" rel="stylesheet" type="text/css">
<title>页面已失效</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<div class="Delete_prompt">
    <div class="T"><img src="${ctx}/static/images/car_delete_pic04.png"></div>
    <div class="M">
        <dl>
            <dt><img src="${ctx}/static/images/car_delete_pic02.png"></dt>
            <dd>页面已失效</dd>
        </dl>
    </div>
    <div class="B">
        <dl>
            <dt><img src="${ctx}/static/images/car_delete_pic03.png"></dt>
            <dd>扫码及时获取更多行情价</dd>
        </dl>
    </div>
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