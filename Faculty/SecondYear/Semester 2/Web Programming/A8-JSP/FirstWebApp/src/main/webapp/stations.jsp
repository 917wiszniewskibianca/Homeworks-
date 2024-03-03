<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transportation Route</title>
    <script>
        function chooseFinalDestination() {
            // Redirect to finalDestination.jsp
            window.location.href = "FinalDestinationServlet";

        }
        function goBack() {
            // Redirect to the previous page
            window.history.back();

            // Remove the last entry from the session attribute list
            fetch("removeLastCity") // Replace "removeLastCity" with the appropriate URL mapping
                .then(response => {
                    if (response.ok) {
                        // Reload the current page to reflect the updated session attribute
                        window.location.reload();
                    } else {
                        console.error("Failed to remove the last city from session.");
                    }
                })
                .catch(error => {
                    console.error("Error occurred while removing the last city from session:", error);
                });
        }

    </script>

    </script>
</head>
<body>
<h1>Stations</h1>
<ul>
    <%
        Integer cityId = (Integer) request.getAttribute("cityId");
        String selectedCity = (String) request.getAttribute("selectedCity");
        ResultSet stations = (ResultSet) request.getAttribute("stations");

        // Display the selected city's name
        if (selectedCity != null) {
            System.out.println("<h2>Selected City: " + selectedCity + "</h2>");
        }

        while (stations.next()) {
            int connectionId = stations.getInt("id");
            System.out.println(connectionId);
            String connectedCity = stations.getString("connected_city");
            %>
            <li>
                <a href="stations?id=<%= connectionId %>"><%= connectedCity %></a>
            </li>
            <%
        }

        // Close the ResultSet
        stations.close();
    %>
</ul>
<br>
<button onclick=goBack()>Go Back</button>
<button onclick="chooseFinalDestination()">Final Destination</button>

</body>
</html>
