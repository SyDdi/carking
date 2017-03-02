/**
 * Created by admin on 2016/12/2.
 */
var weixinUrl = ckdConfig.WEIXINURL;
var wechatAppid = ckdConfig.WECHATAPPID;
var weixin = {
    config: {
        url:'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+wechatAppid+'&redirect_uri='+encodeURIComponent(window.location.href)+'&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect',
        userInfo:JSON.parse(sessionStorage.getItem('CKD_WX_USER')),
        api:weixinUrl
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
                sessionStorage.setItem('CKD_WX_USER',JSON.stringify(json));
            },
            error: function(json) {
            }
        })
    },
    getSessionUser : function(){
        $.ajax({
            type: 'get',
            url: weixin.config.api + '/wx/getSessionUser',
            cache:false,
            async: false,
            dataType: 'jsonp',
            jsonp: 'callback',
            success: function(json) {
                if (json != null) {
                    JSON.stringify(json);
                    sessionStorage.setItem('CKD_WX_USER', JSON.stringify(json));
                    return true;
                }
                return false;
            },
            error: function(json) {
            }
        })
    },
    getUserInfo:function(){
        if(weixin.config.userInfo != null){
            //console.info("A  "+sessionStorage.getItem('CKD_WX_USER'));
            return JSON.parse(sessionStorage.getItem('CKD_WX_USER'));
        }else{
            //console.info("B  ");
            if(weixin.getQueryString('code') != null){
                weixin.getUser(weixin.getQueryString('code'));
                //console.info("C "+sessionStorage.getItem('CKD_WX_USER'));
                return JSON.parse(sessionStorage.getItem('CKD_WX_USER'));
            }else if(weixin.getSessionUser()) {
                //console.info("D "+sessionStorage.getItem('CKD_WX_USER'));
                return JSON.parse(sessionStorage.getItem('CKD_WX_USER'));
            }else{
                //console.info("location")
                window.location.href = weixin.config.url;
            }
        }
    }
}
