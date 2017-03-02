<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>异常页面</title>
<link rel="stylesheet" href="../static/css/weui.css?ts=1420774989">
</head>

<body>

    <div class="page__bd">
        <article class="weui-article">
            <h1>异常：</h1>
            <section>
                <h2 class="title">异常信息</h2>
                <section>
                    <p>
                        这里报出了业务异常：

                        ${ex}
                    </p>
                 </section>
            </section>
        </article>
    </div>


    <div class="weui-footer weui-footer_fixed-bottom">
        <p class="weui-footer__links">
            <a href="javascript:home();" class="weui-footer__link">WeUI首页</a>
        </p>
        <p class="weui-footer__text">Copyright © 2008-2016 weui.io</p>
    </div>

</body>
</html>

<script>

    // A $( document ).ready() block.
    $( document ).ready(function() {
        //weixin.getUserInfo();
       $("#sendMessage").click(function(){
           weixin.getUserInfo();
           $('#userInfo').html(JSON.parse(localStorage.getItem('CKD_WX_USER')).nickname);
       });
    });

</script>



