
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transportation Route</title>
</head>
<body>
<h1>Login successful</h1>
<h2>Choose a City</h2>
<ul>
    <%
        ResultSet cities = (ResultSet) request.getAttribute("cities");

        // Iterate over the cities and display them
        while (cities.next()) {
            int cityId = cities.getInt("id");
            String cityName = cities.getString("name");
            %>
            <li>
                <a href="stations?id=<%= cityId %>"><%= cityName %></a>
            </li>
            <%
        }

        // Close the ResultSet
        cities.close();
    %>
</ul>
</body>
</html>
