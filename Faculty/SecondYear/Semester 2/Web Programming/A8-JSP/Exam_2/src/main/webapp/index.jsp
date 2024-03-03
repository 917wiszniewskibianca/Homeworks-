<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<div align=center>
    <h1> User Login </h1>

    <form action="LoginServlet" method="post">
        <table>
            <tr><td>Enter Username: </td> <td> <input type= text name=txtName></td></tr>
            <tr><td>Enter Mother: </td> <td> <input type= text name=mother></td></tr>
            <tr><td>Enter Father: </td> <td> <input type= text name=father></td></tr>
            <tr> <td><input type=submit value= Login ></td><td><input type=reset></td></tr>
        </table>
    </form>

</div>
</body>
</html>