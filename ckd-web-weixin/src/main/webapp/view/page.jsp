<%@page import="com.car.mp.util.Helper"%>
<%@page import="com.car.mp.util.PageHelper"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String url = request.getParameter("u");
	int pageIndex = Helper.parseInt(request.getParameter("p"));
	int pageSize = Helper.parseInt(request.getParameter("s"));
	int record = Helper.parseInt(request.getParameter("r"));
	String html = PageHelper.getHtml(url, pageIndex, pageSize, record);
%>
<%=html%>