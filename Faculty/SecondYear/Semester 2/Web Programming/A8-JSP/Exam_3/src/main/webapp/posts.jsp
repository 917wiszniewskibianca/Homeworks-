<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts</title>
</head>
<body>
<h1> Posts </h1>
<script>
    // Function to check for updates
    function checkForUpdates() {
        fetch('checkUpdatesServlet')
            .then(response => response.json())
            .then(result => {
                if (result !== null) {
                    // Display the last added post information
                    alert('Last Added Post:\n\nUser: ' + result.user + '\nTopic ' + result.topicid + '\nText: ' + result.text + '\nDate: ' + result.date);
                }
            })
            .catch(error => console.log(error));
    }
    setInterval(checkForUpdates, 5000);
</script>

<%
    ResultSet posts = (ResultSet) request.getAttribute("posts");
    while (posts.next()){
        Integer id = posts.getInt("id");
        String user = posts.getString("user");
        String topicname=posts.getString("topicname");
        String text= posts.getString("text");
        Date date = posts.getDate("date");
%>
<table>
    <tr><td>User:</td>   <td><%= user %>   </td></tr>
    <tr><td> Topic:</td> <td><%= topicname %> </td></tr>
    <tr><td>Text: </td>  <td><%= text %>    </td> </tr>
    <tr><td>Date: </td>  <td><%= date %>    </td></tr>
    <form action="UpdateServlet" method="post" >
        <table>
            <tr><td>Enter Topic: </td> <td> <input type= text name=txtTopic></td></tr>
            <tr><td>Enter Text: </td> <td> <input type= text name=txtText></td></tr>
            <tr><td><input type=submit value= Update ></td></tr>
            <input type="hidden" name=postId value=<%= id %>>
        </table>
    </form>

</table>
<br> </br>
  <%  }  %>

<table>
    <form action="PostsServlet" method="post">
        <table>
            <tr><td>Enter Topic: </td> <td> <input type= text name=addTopic></td></tr>
            <tr><td>Enter Text: </td> <td> <input type= text name=addText></td></tr>
            <tr><td><input type=submit value= Add ></td></tr>
        </table>
    </form>

</table>

</form>
</body>
</html>
