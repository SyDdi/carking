<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.net.URLEncoder" %>
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
    <title>行情价-推荐车源</title>
    <link href="${ctx}/static/css/alarm_car.css" rel="stylesheet"
          type="text/css">
    <link href="${ctx}/static/css/page.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var area = $("#area").val();
                var brand = $("#brand").val();
                var kw = $("#kw").val();
                var url = "${ctx}" + "/carsource/list/1.html";
                var param = "?";
                if ("" != area) {
                    param = param + "area=" + area + "&"
                }
                if ("" != brand) {
                    param = param + "brand=" + brand + "&"
                }
                if ("" != kw) {
                    param = param + "kw=" + kw + "&"
                }
                if (0 < param.indexOf("&")) {
                    param = param.substring(0, param.length - 1);
                }
                if (1 < param.length) {
                    url = encodeURI(url + param);
                }
                self.location = url;
            })
        });
    </script>
    <style type="text/css">
        .kw {
            width: 200px;
            padding: 1px 6px;
            height: 26px;
            line-height: 26px;
            border: 1px solid #AAAAAA;
        }
    </style>
</head>

<body>
<div class="main box top_qh">
    <ul id="ul_qh01Tab">
        <li><img src="${ctx}/static/images/de_top_01.jpg"></li>
        <li><a class="menub2_2" id="menu_2_1" href="${ctx}/alarm/list/1.html">新车价变动</a></li>
        <li><a class="menub2_1" id="menu_2_2" href="${ctx}/carsource/list/1.html">行情价车源</a></li>
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
                        <dd class="w7">关键字</dd>
                        <dt></dt>
                    </dl>
                </div>
                <div class="main box B">
                    <dl>
                        <dd class="w1" style="display: none;">
                            <select name="area">
                                <option value="0">请选择</option>
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
                            <select name="brand" id="brand">
                                <option value="">请选择</option>
                                <c:forEach var="brand" items="${brandlist}" varStatus="status">
                                    <option value="${brand.brand}"
                                            <c:if test="${brandName==brand.brand}">
                                                selected="selected"
                                            </c:if>>${brand.brand}</option>
                                </c:forEach></select>
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
                        <dd class="w7">
                            <input name="kw" id="kw" type="text" class="kw"
                                   value="${kw}">
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
            <div class="main de_header_lb">
                <dl>
                    <dt>来源</dt>
                    <dd class="w1">上架时间</dd>
                    <dd class="w2">所在地</dd>
                    <dd class="w3">车型</dd>
                    <dd class="w4">牌照属地</dd>
                    <dd class="w5">上牌日期</dd>
                    <dd class="w6">行驶里程(万公里)</dd>
                    <dd class="w7">车主报价</dd>
                    <dd class="w8">销售价(估)</dd>
                    <dd class="w9" style="display: none;">预计利润</dd>
                    <dd class="w9">收购价(估)</dd>
                </dl>
            </div>
            <div class="clear"></div>
        </div>
    </div>

    <div class="main box de_top_bg"></div>

    <div class="wrap_1200">
        <div class="main de_body_lst">
            <ul>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <li>
                        <dl>
                            <dt>
                                <c:choose>
                                    <c:when test="${item.srcName=='瓜子车'}">
                                        <img src="${ctx}/static/images/cc_gz.jpg" align="absmiddle">
                                    </c:when>
                                    <c:when test="${item.srcName=='人人车'}">
                                        <img src="${ctx}/static/images/cc_rr.jpg" align="absmiddle">
                                    </c:when>
                                </c:choose>
                            </dt>
                            <dd class="w1">
                                <fmt:formatDate value="${item.findDay}" pattern="yyyy/MM/dd"/>
                            </dd>
                            <dd class="w2">${item.carArea}</dd>
                            <dd class="w3">
                                <a href="${item.url}" target="_blank" title="${ item.carMode }">${ item.carMode }</a>
                            </dd>
                            <dd class="w4">${item.licenseArea}</dd>
                            <dd class="w5">
                                <fmt:formatDate value="${item.licenseDay}" pattern="yyyy/MM"/>
                            </dd>
                            <dd class="w6">
                                <fmt:formatNumber type="number" pattern="0.00"
                                                  value="${item.mileage}"/>
                            </dd>
                            <dd class="w7">
                                <fmt:formatNumber type="number" pattern="0.00"
                                                  value="${item.quotedPrice}"/>
                            </dd>
                            <dd class="w8">
                                <fmt:formatNumber type="number" pattern="0.00"
                                                  value="${item.sellPrice}"/>
                            </dd>
                            <dd class="w9" style="display: none;">
                                <fmt:formatNumber type="number" pattern="0.00%"
                                                  value="${item.profitRate}"/>
                            </dd>
                            <dd class="w9">
                                <fmt:formatNumber type="number" pattern="0.00"
                                                  value="${item.buyPrice}"/>
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
                Object kw = request.getParameter("kw");
                Object brand = request.getParameter("brand");
                Object areaId = request.getParameter("area");
                String param = "--";
                if (null != areaId && StringUtils.isNotBlank(areaId.toString())) {
                    param = param + "area=" + areaId + "---";
                }
                if (null != brand && StringUtils.isNotBlank(brand.toString())) {
                    param = param + "brand=" + brand + "---";
                }
                if (null != kw && StringUtils.isNotBlank(kw.toString())) {
                    param = param + "kw=" + kw + "---";
                }
                if (0 < param.indexOf("---")) {
                    param = param.substring(0, param.length() - 3);
                }
                if (2 < param.length()) {
                    // param = URLEncoder.encode(param);
                } else {
                    param = "";
                }

                String u = "@pageIndex.html" + param;
                String url = "/view/page.jsp?p=" + p + "&r=" + r + "&s=" + s + "&u=" + u;
            %>
            <jsp:include page="<%=url%>"></jsp:include>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script>
    window.onscroll = function () {
        var sl = -Math.max(document.body.scrollLeft, document.documentElement.scrollLeft);
        document.getElementById('fixed').style.left = sl + 'px';
    }
</script>
</body>
</html>
