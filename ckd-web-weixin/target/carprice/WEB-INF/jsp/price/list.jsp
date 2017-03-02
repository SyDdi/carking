<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <title>行情价-汽车价格权威</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${ctx}/static/css/m_xcj.css?20161029" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/getscript.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/iscroll-probe.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/price.js?20161031"></script>

    <style>
        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        html {
            -ms-touch-action: none;
        }
        body,ul,li {
            padding: 0;
            margin: 0;
            border: 0;
        }

        body {
            /*font-size: 12px;*/
            /*font-family: ubuntu, helvetica, arial;*/
            overflow: hidden;
            /* this is important to prevent the whole page to bounce */
        }

        #header {
            position: absolute;
            z-index: 2;
            top: 0;
            left: 0;
            width: 100%;
            height: 45px;
            line-height: 45px;
            background: #CD235C;
            padding: 0;
            color: #eee;
            font-size: 20px;
            text-align: center;
            font-weight: bold;
        }

        #footer {
            position: absolute;
            z-index: 2;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 48px;
            background: #444;
            padding: 0;
            border-top: 1px solid #444;
        }

        #content {
            position: absolute;
            z-index: 1;
            top: 95px;
            bottom: 0px;
            left: 0;
            width: 100%;
            overflow: hidden;
        }

        #scroller {
            position: absolute;
            z-index: 1;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
            width: 100%;
            -webkit-transform: translateZ(0);
            -moz-transform: translateZ(0);
            -ms-transform: translateZ(0);
            -o-transform: translateZ(0);
            transform: translateZ(0);
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            -webkit-text-size-adjust: none;
            -moz-text-size-adjust: none;
            -ms-text-size-adjust: none;
            -o-text-size-adjust: none;
            text-size-adjust: none;
            -webkit-transform:translate3d(0,0,0);
        }

        #scroller ul {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
            text-align: left;
        }

        #scroller li {
            padding: 0 10px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #fff;
            background-color: #fafafa;
            font-size: 14px;
        }
        #pullDown,#pullUp {
            height: 40px;
            line-height: 40px;
            padding: 5px 10px;
            font-weight: bold;
            font-size: 14px;
            color: #888;
        }

        #pullDown .pullDownIcon,#pullUp .pullUpIcon {
            display: block;
            float: left;
            width: 40px;
            height: 40px;
            background: url(img/pull-icon@2x.png) 0 0 no-repeat;
            -webkit-background-size: 40px 80px;
            background-size: 40px 80px;
            -webkit-transition-property: -webkit-transform;
            -webkit-transition-duration: 250ms;
        }

        #pullDown .pullDownIcon {
            -webkit-transform: rotate(0deg) translateZ(0);
        }

        #pullUp .pullUpIcon {
            -webkit-transform: rotate(-180deg) translateZ(0);
        }

        #pullDown.flip .pullDownIcon {
            -webkit-transform: rotate(-180deg) translateZ(0);
        }

        #pullUp.flip .pullUpIcon {
            -webkit-transform: rotate(0deg) translateZ(0);
        }

        #pullDown.loading .pullDownIcon,#pullUp.loading .pullUpIcon {
            background-position: 0 100%;
            -webkit-transform: rotate(0deg) translateZ(0);
            -webkit-transition-duration: 0ms;
            -webkit-animation-name: loading;
            -webkit-animation-duration: 2s;
            -webkit-animation-iteration-count: infinite;
            -webkit-animation-timing-function: linear;
        }
        .-webkit-keyframes loading {
        from {
            -webkit-transform:rotate(0deg)translateZ(0);
        }

        .to {
            -webkit-transform: rotate(360deg) translateZ(0);
        }
        }
    </style>

</head>

<body onload="loaded()">
<header>
    <div class="main box top_banner">
        <form action="${ctx}/price/list.html" id="mainFrm" method="get">
            <div class="main L">
                <dl><dt><img src="${ctx}/static/images/hqj_xcj_pic01.png"></dt><dd><div id="area">${areaName}</div></dd></dl>
            </div>
            <div class="side R">
                <dl><dt><input type="text" name="q" id="q" class="srk" value="${q}" placeholder="请输入品牌车系"></dt>
                    <dd><input type="submit" value="查询" class="btn"></dd></dl>
            </div>
            <input type="hidden" name="area"  id="areaId" value="${area}">
        </form>
        <input type="hidden" name="curPage"  id="curPage" value="${curPage}">
        <input type="hidden" name="totalPage"  id="totalPage" value="${totalPage}">
    </div>
    <div class="main box sj">日期：<fmt:formatDate value="${yesterday}" pattern="yyyy-MM-dd"/></div>
</header>

<div id="bg"></div>
<div class="payment_map_mask">
    <ol>关闭</ol>
    <ul id="areaScope"></ul>
</div>

<div id="content">
    <div id="scroller">
        <div id="pullDown" class="ub ub-pc c-gra">
            <div class="pullDownIcon"></div>
            <div class="pullDownLabel">下拉刷新</div>
        </div>
        <div id="add">

            <c:choose>
                <c:when test="${list== null || fn:length(list) == 0}">
                    <section>
                        <div class="intro_xcj_zwsj">
                            <ul>
                                <li><img src="${ctx}/static/images/intro_gth.png" align="absmiddle">暂无数据</li>
                            </ul>
                        </div>
                    </section>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${list}" varStatus="status">
                        <section>
                            <div class="box xcj_zt_xx">${item.area}<span>|</span>${item.factory}<span>|</span>${item.carLine}<span>|</span>${item.year}  ${item.carModel}</div>
                            <div class="box xcj_zt_jg">
                                <dl>
                                    <dt>昨日均价<br><span><fmt:formatNumber value="${item.todayPrice}" type="currency" pattern="0.00"/>万元</span></dt>
                                    <dd>7天前:<fmt:formatNumber value="${item.weekAgoPrice}" type="currency" pattern="0.00"/>万元<br>变化率:<span class="<c:choose><c:when test="${item.weekDiffPrice>0}">ora</c:when><c:when test="${item.weekDiffPrice<0}">gre</c:when></c:choose> "><fmt:formatNumber type="number" pattern="0.00%" value="${item.weekDiffPrice}" />
                    <c:choose>
                        <c:when test="${item.weekDiffPrice>0}"><img src="${ctx}/static/images/hqj_xcj_pic03.png" align="absmiddle"></c:when>
                        <c:when test="${item.weekDiffPrice<0}"><img src="${ctx}/static/images/hqj_xcj_pic02.png" align="absmiddle"></c:when>
                    </c:choose>
                    </span></dd>
                                    <dd>30天前:<fmt:formatNumber value="${item.monthAgoPrice}" type="currency" pattern="0.00"/>万元<br>变化率:<span class="<c:choose><c:when test="${item.monthDiffPrice>0}">ora</c:when><c:when test="${item.monthDiffPrice<0}">gre</c:when></c:choose> "><fmt:formatNumber type="number" pattern="0.00%" value="${item.monthDiffPrice}" />
                    <c:choose>
                        <c:when test="${item.monthDiffPrice>0}"><img src="${ctx}/static/images/hqj_xcj_pic03.png" align="absmiddle"></c:when>
                        <c:when test="${item.monthDiffPrice<0}"><img src="${ctx}/static/images/hqj_xcj_pic02.png" align="absmiddle"></c:when>
                    </c:choose>
                    </span></dd>
                                </dl>
                            </div>
                        </section>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="pullUp" class="ub ub-pc c-gra">
            <div class="pullUpIcon"></div>
            <div class="pullUpLabel">上拉显示更多...</div>
        </div>
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
