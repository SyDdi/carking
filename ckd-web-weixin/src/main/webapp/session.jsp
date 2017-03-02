<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>


Server Info:
<%
    out.println(request.getLocalAddr() + " : " + request.getLocalPort()+" ");%>
<%
    out.println("ID " + session.getId()+" ");
            // 如果有新的 Session 属性设置
            String dataName = request.getParameter("dataName");
    if (dataName != null && dataName.length() > 0) {
        String dataValue = request.getParameter("dataValue");
        session.setAttribute(dataName, dataValue);
    }
    out.print("Session 列表");
    Enumeration e = session.getAttributeNames();
    while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String value = session.getAttribute(name).toString();
        out.println( name + " = " + value+" ");
        //System.out.println( name + " = " + value);
    }
%>