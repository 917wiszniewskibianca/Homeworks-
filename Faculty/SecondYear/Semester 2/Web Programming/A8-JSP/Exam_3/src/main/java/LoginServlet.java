import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("txtName");
        session.setAttribute("username", name);
        String url = "jdbc:mysql://localhost:8889/exam_3";
        String username = "Bianca";
        String password = "muzicuta123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            String currentNumElementsQuery = "SELECT COUNT(*) AS numElements FROM Posts";
            PreparedStatement currentNumElementsStmt = conn.prepareStatement(currentNumElementsQuery);
            ResultSet currentNumElementsResult = currentNumElementsStmt.executeQuery();

            int currentNumElements = 0;
            if (currentNumElementsResult.next()) {
                currentNumElements = currentNumElementsResult.getInt("numElements");
            }
            session.setAttribute("nrElements",currentNumElements);

            response.sendRedirect("PostsServlet");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
