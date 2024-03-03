<%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1> Add new Product</h1>
    <table>
        <form action="AddFamilyServlet" method="post">
            <table>
                <tr><td>Enter Mother: </td> <td> <input type= text name=addMother></td></tr>
                <tr><td>Enter Father: </td> <td> <input type= text name=addFather></td></tr>
                <br> </br>
                <tr><td><input type=submit value= Add ></td></tr>
            </table>
        </form>
    </table>
    <br> </br>
    <form action="FindFamilyServlet" method="post">
        <tr> <td><input type=submit value= "Find father family line" ></td></tr>
    </form>
    <form action="FindFamilyServlet" method="get">
        <tr> <td><input type=submit value= "Find mother family line" ></td></tr>
    </form>
</div>
</body>
</html>
