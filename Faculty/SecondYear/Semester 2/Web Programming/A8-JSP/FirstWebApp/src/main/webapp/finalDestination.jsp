<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<body>
<h1>Final Destination</h1>
      <%
        // Retrieve the list of selected cities from session attribute

        ArrayList<String> selectedCities = (ArrayList<String>) request.getAttribute("selectedCities");

        if (selectedCities != null && !selectedCities.isEmpty()) {
      %>
<h2>Selected Cities:</h2>
<ul>
      <%
        for (String city : selectedCities) {
          String message = city; // Your variable with a value

          // Assign the variable value to a request attribute
          request.setAttribute("message", message);
      %>
          <li> ${message} </li>
       <% } %>

</ul>
      <%
        } else {
          System.out.println("<p>No cities selected.</p>");
        }
      %>

<form action="LogoutServlet" method="post">
        <tr> <td><input type=submit value= Logout ></td></tr>
</form>
</body>
</html>
