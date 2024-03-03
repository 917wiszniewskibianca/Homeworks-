package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import java.io.IOException;

@WebServlet(name = "CitySelectionServlet", value = "/cities")
public class CitySelectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String session_username = (String)session.getAttribute("username");
        if(session_username == null)
        { request.getRequestDispatcher("").forward(request,response);}
        else {
            String url = "jdbc:mysql://localhost:8889/transportation";
            String username = "Bianca";
            String password = "muzicuta123";
            try {
                Class.forName("com.mysql.jdbc.Driver"); // load the mysql jdbc driver
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM City";
                ResultSet rs = stmt.executeQuery(query);
                request.setAttribute("cities", rs);// Store the cities in the request attribute

                // Forward the request to cities.jsp
                request.getRequestDispatcher("cities.jsp").forward(request, response);

                // Close the database resources
                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}



