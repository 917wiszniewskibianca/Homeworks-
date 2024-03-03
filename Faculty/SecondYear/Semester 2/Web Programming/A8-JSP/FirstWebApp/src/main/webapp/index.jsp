<%--
  Created by IntelliJ IDEA.
  User: biancawiszniewski
  Date: 19.06.2023
  Time: 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align=center>
    <h1> User Login </h1>

    <form action="login" method="post">
        <table>
            <tr><td>Enter Username: </td> <td> <input type= text name=txtName></td></tr>
            <tr><td>Enter Passowrd: </td> <td> <input type= password name=txtPwd></td></tr>
            <tr> <td><input type=submit value= Login ></td><td><input type=reset></td></tr>
        </table>
    </form>
</div>
</body>

</html>
