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
<title>车源详细页面</title>
<link rel="stylesheet" href="../static/css/weui.css?ts=1420774989">
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>

<script>
    var weixin = {
        config: {
            url:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcd1e24f5b6cbfeb5&redirect_uri='+encodeURIComponent(window.location.href)+'&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect',
            userInfo:JSON.parse(localStorage.getItem('CKD_WX_USER')),
            api:'http://syncml.tunnel.qydev.com'
        },
        isweixin: function() {
            var ua = window.navigator.userAgent.toLowerCase();
            if(ua.match(/MicroMessenger/i) == 'micromessenger'){
                return true;
            } else {
                return false;
            }
        },
        getQueryString: function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null) return unescape(r[2]); return null;
        },
        getUser : function(code) {
            $.ajax({
                type: 'get',
                url: weixin.config.api + '/wx/getOAuth2User?code='+code,
                cache:false,
                async: false,
                dataType: 'jsonp',
                jsonp: 'callback',
                success: function(json){
                    JSON.stringify(json);
                    console.log(json);
                    localStorage.setItem('CKD_WX_USER',JSON.stringify(json));
                },
                error: function(json) {
                    console.log(json);
                }
            })
        },
        getUserInfo:function(){
            if(weixin.config.userInfo != null){
                return JSON.parse(localStorage.getItem('CKD_WX_USER'));
                console.log("A");
            }else{
                console.log("B");
                if(weixin.getQueryString('code') != null){
                    weixin.getUser(weixin.getQueryString('code'));
                    return JSON.parse(localStorage.getItem('CKD_WX_USER'));
                }else{
                    window.location.href = weixin.config.url;
                }
            }
        }
    }
</script>

</head>

<body>

    <div class="page__bd">
        <article class="weui-article">
            <h1>车源详情</h1>
            <section>
                <h2 class="title">奥迪 A4L 2015款 时尚型</h2>
                <section>
                    <p>
                        个人一手车，车况好，开了2年3万公里。家庭用车。买到赚到
                    </p>
                    <div id="userInfo">用户昵称</div>
                 </section>
            </section>
        </article>
    </div>

    <div class="page__bd">

        <div class="weui-cells__title">留言</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入文本" rows="3"></textarea>
                    <div class="weui-textarea-counter"><span>0</span>/200</div>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:;" id="sendMessage">确定</a>
        </div>
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
        weixin.getUserInfo();
       $("#sendMessage").click(function(){
           weixin.getUserInfo();
           $('#userInfo').html(JSON.parse(localStorage.getItem('CKD_WX_USER')).nickname);
       });
    });

</script>



