<%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<div align="center">
    <h1> Add new Product</h1>
    <table>
        <form action="AddProductServlet" method="post">
            <table>
                <tr><td>Enter Name: </td> <td> <input type= text name=addName></td></tr>
                <tr><td>Enter Description: </td> <td> <input type= text name=addDescription></td></tr>
                <br> </br>
                <tr><td><input type=submit value= Add ></td></tr>
            </table>
        </form>
    </table>
    <br> </br>
    <form action="SearchProductServlet" method="get">
            <tr><td>Enter name :  </td> <td> <input type= text name=searchName></td> <td><input type=submit value= Search ></td></tr>
    </form>

    <br> </br>
    <form action="order.jsp">
        <tr><td><input type=submit value= "Create Command" ></td></tr>
    </form>
    <br> </br>

    <form action="CreateOrderServlet" method="post">
        <tr><td><input type=submit value= "Finalize command" ></td></tr>
    </form>

    <form action="CancelOrderServlet" method="get">
        <tr><td><input type=submit value= "Cancel command" ></td></tr>
    </form>

</div>
</body>
</html>
