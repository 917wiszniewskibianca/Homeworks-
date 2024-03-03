import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "SearchProductServlet", value = "/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String session_username = (String) session.getAttribute("username");

        if (session_username == null) {

            request.getRequestDispatcher("").forward(request, response);
        } else {

            String url = "jdbc:mysql://localhost:8889/exam_1";
            String username = "Bianca";
            String password = "muzicuta123";

            String name = request.getParameter("searchName");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                String value = name + "%";
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE name LIKE ?");
                ps.setString(1,value);
                ResultSet result = ps.executeQuery();

                request.setAttribute("products",result);
                request.getRequestDispatcher("search.jsp").forward(request,response);
                conn.close();

            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
