<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Line</title>
</head>
<body>
<h1>This is the father descending line </h1>
<ul>
    <%   ArrayList<String> result = (ArrayList<String>) request.getAttribute("motherLine");
        if (result != null && !result.isEmpty()){
            for (String name : result) {
                String message = name;
                request.setAttribute("message", message);
    %>

    <li> ${message} </li>

    <% }%>
</ul>
<%
    } else {
        System.out.println("<p>No descendents </p>");
    }
%>

</body>
</html>
