import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "AddProductServlet", value = "/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String session_username = (String) session.getAttribute("username");

        if (session_username == null) {

            request.getRequestDispatcher("").forward(request, response);
        } else {

            String url = "jdbc:mysql://localhost:8889/exam_1";
            String username = "Bianca";
            String password = "muzicuta123";

            String name = request.getParameter("addName");
            String description = request.getParameter("addDescription");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Products (name,description) VALUES (?,?)");
                ps.setString(1, name);
                ps.setString(2, description);
                ps.executeUpdate();
                request.getRequestDispatcher("products.jsp").forward(request,response);
                conn.close();

            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
