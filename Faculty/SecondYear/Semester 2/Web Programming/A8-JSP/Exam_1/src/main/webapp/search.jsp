<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1> Products </h1>
<%
    ResultSet products = (ResultSet) request.getAttribute("products");
    while (products.next()){
        String name = products.getString("name");
        String description =products.getString("description");

%>
<table>
    <tr><td>Name:</td>   <td><%= name %>   </td></tr>
    <tr><td> Description:</td> <td><%= description %> </td></tr>

</table>

<%  }  %>
</body>
</html>
