<%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>order</title>
</head>
<body>
<div align="center ">
    <h1> Create an order </h1>
    <form action="CreateOrderServlet" method="get">
        <table>
            <tr><td>Enter Product: </td> <td> <input type= text name=addProduct></td></tr>
            <tr><td>Enter Quantity: </td> <td> <input type= text name=addQuantity></td></tr>
            <br> </br>
            <tr><td><input type=submit value= Add ></td></tr>
        </table>
    </form>
</div>
</body>
</html>
