<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String basePath = request.getScheme() + "://" + request.getServerName();
    int port = request.getServerPort();
    if (80 != port) {
        basePath = basePath + ":" + port;
        basePath = basePath + request.getContextPath();
    }
    if (!basePath.endsWith("/")) {
        basePath = basePath + "/";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <title>行情价-新车价格报警</title>
    <link href="${ctx}/static/css/alarm_car.css" rel="stylesheet"
          type="text/css">
    <link href="${ctx}/static/css/page.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var area = $("#area").val();
                var date = "${date}";
                var url = "${ctx}" + "/alarm/list/";
                if ("" != area) {
                    url = url + area + "/";
                }
                if ("" != date) {
                    url = url + date + "/";
                }
                url = url + "1.html";
                self.location = url;
            })
        });
    </script>
</head>

<body>
<div class="main box top_qh">
    <ul id="ul_qh01Tab">
        <li><img src="${ctx}/static/images/de_top_01.jpg"></li>
        <li><a class="menub2_1" id="menu_2_1" href="${ctx}/alarm/list/1.html">新车价变动</a></li>
        <li><a class="menub2_2" id="menu_2_2" href="${ctx}/carsource/list/1.html">行情价车源</a></li>
        <li><img src="${ctx}/static/images/de_top_02.jpg"></li>
    </ul>
</div>
<div id="menutab_2_1">
    <div class="header" id="fixed">
        <div class="wrap_1200 pt6">
            <div class="main de_header_fl">
                <div class="main box T">
                    <dl>
                        <dd class="w1" style="display: none;">省/自治区/直辖市</dd>
                        <dd class="w2">所属地区</dd>
                        <dd class="w3" style="display: none;">品牌</dd>
                        <dd class="w4" style="display: none;">厂商</dd>
                        <dd class="w5" style="display: none;">车系</dd>
                        <dd class="w6" style="display: none;">年款</dd>
                        <dd class="w7" style="display: none;">车型</dd>
                        <dt></dt>
                    </dl>
                </div>
                <div class="main box B">
                    <dl>
                        <dd class="w1" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dd class="w2">
                            <select id="area" name="">
                                <option value="">请选择</option>
                                <c:forEach var="area" items="${arealist}" varStatus="status">
                                    <option value="${area.carAreaId}"
                                            <c:if test="${areaId==area.carAreaId}">
                                                selected="selected"
                                            </c:if>>${area.carAreaName}</option>
                                </c:forEach></select>
                        </dd>
                        <dd class="w3" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dd class="w4" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dd class="w5" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dd class="w6" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dd class="w7" style="display: none;">
                            <select name="">
                                <option>请选择</option>
                            </select>
                        </dd>
                        <dt>
                            <input name="提交" id="submit" type="submit" value="">
                        </dt>
                    </dl>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="wrap_1200 pt15">
            <div class="main box header_d1h">
                <dl>
                    <dt>
                        <img src="${ctx}/static/images/pic_01.jpg">
                    </dt>
                    <dd class="w1">日期：${date}</dd>
                    <dd class="w2">
                        <img src="${ctx}/static/images/pic_1020.jpg">
                    </dd>
                    <dd class="w3">经销商价格</dd>
                    <dt>
                        <img src="${ctx}/static/images/pic_02.jpg">
                    </dt>
                </dl>
            </div>
            <div class="main box header_d2h">
                <div class="main L">
                    <dl>
                        <dt>地区</dt>
                        <dd class="w1">品牌</dd>
                        <dd class="w2">厂家</dd>
                        <dd class="w3">车系</dd>
                        <dd class="w4">年款</dd>
                        <dd class="w5">车型</dd>
                    </dl>
                </div>
                <div class="side R">
                    <dl>
                        <dt>今日价格</dt>
                        <dd class="w1">昨日价格</dd>
                        <dd class="w2">变化率 <img src="${ctx}/static/images/xjt01.png" align="absmiddle"></dd>
                        <dd class="w3">7天前价格</dd>
                        <dd class="w4">变化率</dd>
                        <dd class="w5">30天前价格</dd>
                        <dd class="w6">变化率</dd>
                    </dl>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>

    <div class="main box top_bg"></div>

    <div class="wrap_1200">
        <div class="main box header_bg"></div>
        <div class="main body_list">
            <ul>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <li>
                        <dl>
                            <dt>${item.area}</dt>
                            <dd class="w1">${item.brand}</dd>
                            <dd class="w2">${item.factory}</dd>
                            <dd class="w3">${item.carLine}</dd>
                            <dd class="w4">${item.year}</dd>
                            <dd class="w5" title="${item.carModel}">${item.carModel}</dd>
                            <dd class="w6">${item.todayPrice}</dd>
                            <dd class="w7">${item.yesterdayPrice}</dd>
                            <dd class="w8">
                                <fmt:formatNumber type="number" pattern="0.00%"
                                                  value="${item.dayDiffPrice}"/>
                                <c:choose>
                                    <c:when test="${item.dayDiffPrice>0}">
                                        <img src="${ctx}/static/images/sjt.png" align="absmiddle">
                                    </c:when>
                                    <c:when test="${item.dayDiffPrice<0}">
                                        <img src="${ctx}/static/images/xjt.png" align="absmiddle">
                                    </c:when>
                                </c:choose>

                            </dd>
                            <dd class="w9">${item.weekAgoPrice}</dd>
                            <dd class="w10">
                                <fmt:formatNumber type="number" pattern="0.00%"
                                                  value="${item.weekDiffPrice}"/>
                                <c:choose>
                                    <c:when test="${item.weekDiffPrice>0}">
                                        <img src="${ctx}/static/images/sjt.png" align="absmiddle">
                                    </c:when>
                                    <c:when test="${item.weekDiffPrice<0}">
                                        <img src="${ctx}/static/images/xjt.png" align="absmiddle">
                                    </c:when>
                                </c:choose>
                            </dd>
                            <dd class="w11">${item.monthAgoPrice}</dd>
                            <dd class="w12">
                                <fmt:formatNumber type="number" pattern="0.00%"
                                                  value="${item.monthDiffPrice}"/>
                                <c:choose>
                                    <c:when test="${item.monthDiffPrice>0}">
                                        <img src="${ctx}/static/images/sjt.png" align="absmiddle">
                                    </c:when>
                                    <c:when test="${item.monthDiffPrice<0}">
                                        <img src="${ctx}/static/images/xjt.png" align="absmiddle">
                                    </c:when>
                                </c:choose>
                            </dd>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
            <%
                Object p = request.getAttribute("page");
                Object r = request.getAttribute("record");
                Object s = request.getAttribute("size");
                Object id = request.getParameter("id");
                String u = "@pageIndex.html";
                String url = "/view/page.jsp?p=" + p + "&r=" + r + "&s=" + s + "&u=" + u;
            %>
            <jsp:include page="<%=url%>"></jsp:include>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script>
    window.onscroll = function () {
        var sl = -Math.max(document.body.scrollLeft,
                document.documentElement.scrollLeft);
        document.getElementById('fixed').style.left = sl + 'px';
    }
</script>
</body>
</html>
