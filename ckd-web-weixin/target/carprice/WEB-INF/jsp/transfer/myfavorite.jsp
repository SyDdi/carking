<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <title>我的车-行情价</title>
    <%@ include file="/WEB-INF/jsp/common/config.jsp"%>
    <link href="${ctx}/static/css/css.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/mycar/delete.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/mycar/jquery.mycar.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/weixin/weixinToken.js"></script>
    <%--<script type="text/javascript" src="${ctx}/static/js/sweet-alert.js"></script>--%>
    <%--<link rel="stylesheet" href="${ctx}/static/css/sweet-alert.css">--%>
</head>
<script>
    var json = weixin.getUserInfo();//验证是否登录微信
</script>
<body>
<header>
    <input type="hidden" id="userId" value="${userId}">
    <div class="menu_mc">
        <dl>
            <dd class="fh"><a href="#"><!--<img src="${ctx}/static/images/header_fh.png">--></a></dd>
            <dt>我的车</dt>
            <dd></dd>
        </dl>
    </div>
</header>

<section class="mpT49 wbox">
    <div class="main box my_car_tit">
        <ul>
            <li><a class="menub2_1" id="menu_2_1" onclick="showtabs2(2,1,2); findMycar();">我的车</a></li>
            <li><a class="menub2_2" id="menu_2_2" onclick="showtabs2(2,2,2); findFavorite();">我的收藏</a></li>
        </ul>
    </div>
</section>

<div id="menutab_2_1" class="main box my_car_list">
    <div class="list" id="menutab_2_1_list">


    </div>
    <div class="add">
        <dl><a href="/transfer/newCar"><dt><img src="${ctx}/static/images/intro_basic_jh.png"></dt><dd>添加车辆</dd></a></dl>
    </div>
</div>


<div id="menutab_2_2" style="display:none;" class="main box my_car_collect">
    <div class="list" id="menutab_2_2_list">


    </div>
</div>
<div id="collect_zs" style="display:none;"></div>
</body>
</html>
<script>

    $(function(){
        if(json != null) {
            $("#userId").val(json.id);
        }
        findMycar();
//        findFavorite();
    });
    /**
     * 查询我的车
     */
    function findMycar() {
        var userId = $("#userId").val();
        $.ajax({
            url: "${ctx}/my/car",
            type: "GET",
            dataType: "json",
            data: "userId=" + userId,
            success: function (data) {
                var strHtml = '';
                for(var i in data.data) {
                    var vhcl = data.data[i];
                    var isShow = "",isShowName = "展示中";
                    if(vhcl.status == 0){
                        isShow = "xj";
                        isShowName = "下架";
                    }
                    strHtml +=
                            '<div class="line-wrapper">' +
                                '<div class="line-scroll-wrapper">' +
                                    '<div class="line-normal-wrapper">' +
                                        '<div class="line-normal-left-wrapper '+isShow+'">' +
                                            '<div class="line-normal-avatar-wrapper"><em>'+ isShowName +'</em></div>'+
                                            '<div class="line-normal-info-wrapper"><a href="${ctx}/car/'+vhcl.hashId+'.html">'+vhcl.model.carYear+'款 '+vhcl.model.brand+'&nbsp;'+vhcl.model.familyGroupName+'&nbsp;'+vhcl.model.family+'&nbsp;'+vhcl.model.shortName+' </a></div>' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="line-btn-delete" id="line-btn-delete" name="line-btn-delete" value="'+vhcl.id+'"><button>删除</button></div>' +
                                '</div>' +
                            '</div>' ;

                }
                $("#menutab_2_1_list").html(strHtml);
                //showtabs2(2,1,2);
                ready();
            }
        })
    }
    /**
     * 查询我的收藏
     */
     function findFavorite() {
        var userId = $("#userId").val();
        $.ajax({
            url: "${ctx}/my/favorite",
            type: "GET",
            dataType: "json",
            data: "userId=" + userId,
            success: function (data) {
                var strHtml = '';
                for(var i in data.data) {
                    var coll = data.data[i];
                    var isShow = "",isShowName = "展示中";
                    if(coll.vehicle.status == 0){
                    	isShow = "xj";
                        isShowName = "下架";
                        isShowName = "下架";
                    }
                    strHtml +=
                            '<div class="line-wrapper">' +
                                '<div class="line-scroll-wrapper">' +
                                    '<div class="line-normal-wrapper">' +
                                        '<div class="line-normal-left-wrapper '+isShow+'">' +
                                            '<div class="line-normal-avatar-wrapper"><em>'+ isShowName +'</em></div>' +
											'<div class="line-normal-info-wrapper"><a href="${ctx}/car/'+coll.vehicle.hashId+'.html">'+coll.vehicle.model.carYear+'款 '+coll.vehicle.model.brand+'&nbsp;'+coll.vehicle.model.familyGroupName+'&nbsp;'+coll.vehicle.model.family+'&nbsp;'+coll.vehicle.model.shortName+' </a></div>' +
										'</div>' +
                                    '</div>' +
                                    '<div class="line-btn-delete" id="line-btn-collect" name="line-btn-collect" value="'+coll.id+'"><button>取消<br>收藏</button></div>' +
                                '</div>' +
                            '</div>' ;
                }
                $("#menutab_2_2_list").html(strHtml);
                ready();
            }
        }) 
    }
</script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?fed9ca6874b32c074b1602d4e1a78656";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

