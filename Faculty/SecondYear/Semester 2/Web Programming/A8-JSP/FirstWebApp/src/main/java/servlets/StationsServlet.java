package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "StationsServlet", value = "/stations")
public class StationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cityIdParam = request.getParameter("id");
        HttpSession session = request.getSession();
        String session_username = (String)session.getAttribute("username");
        if(session_username == null)
        { request.getRequestDispatcher("").forward(request,response);}
        else {

            if (cityIdParam != null) {
                int cityId = Integer.parseInt(cityIdParam);

                // Database connection details
                String url = "jdbc:mysql://localhost:8889/transportation";
                String username = "Bianca";
                String password = "muzicuta123";

                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // Establish the database connection
                    Connection conn = DriverManager.getConnection(url, username, password);

                    // Create a statement
                    Statement stmt = conn.createStatement();

                    // Retrieve the selected city's name
                    String cityQuery = "SELECT name FROM City WHERE id = " + cityId;
                    ResultSet cityResult = stmt.executeQuery(cityQuery);

                    // Store the selected city's name in the request attribute
                    if (cityResult.next()) {
                        request.setAttribute("cityId", cityId);
                        String cityName = cityResult.getString("name");
                        request.setAttribute("selectedCity", cityName);

                        // Store the selected city in the user's list of selected cities

                        ArrayList<String> selectedCities = (ArrayList<String>) session.getAttribute("selectedCities");
                        if (selectedCities == null) {
                            selectedCities = new ArrayList<>();
                        }
                        selectedCities.add(cityName);
                        session.setAttribute("selectedCities", selectedCities);
                    }

                    // Retrieve the stations connected to the selected city
                    String stationsQuery1 = "SELECT con.id, c.name AS connected_city "
                            + "FROM Connection con "
                            + "JOIN City c ON con.destination_city_id = c.id "
                            + "WHERE con.source_city_id = " + cityId;
                    String stationsQuery = "SELECT c.id, c.name AS connected_city " +
                            "FROM City c " +
                            "WHERE id IN (SELECT con.destination_city_id " +
                            "             FROM Connection con " +
                            "             WHERE con.source_city_id = " + cityId + " )";

                    ResultSet stationsResult = stmt.executeQuery(stationsQuery);


                    // Store the stations in the request attribute
                    request.setAttribute("stations", stationsResult);

                    // Forward the request to stations.jsp
                    request.getRequestDispatcher("stations.jsp").forward(request, response);

                    // Close the database resources
                    stationsResult.close();
                    cityResult.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
