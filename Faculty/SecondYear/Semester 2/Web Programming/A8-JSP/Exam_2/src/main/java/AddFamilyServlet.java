import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "AddFamilyServlet", value = "/AddFamilyServlet")
public class AddFamilyServlet extends HttpServlet {
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

            String url = "jdbc:mysql://localhost:8889/exam_2";
            String username = "Bianca";
            String password = "muzicuta123";

            String mother = request.getParameter("addMother");
            String father = request.getParameter("addFather");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement ps1 = conn.prepareStatement("Select mother , father from FamilyRelations where username = ?");
                ps1.setString(1,session_username);
                ResultSet rs= ps1.executeQuery();
                if(rs.next()){
                    PreparedStatement ps = conn.prepareStatement("UPDATE FamilyRelations SET mother = ? , father= ? WHERE username = ?");
                    ps.setString(1, mother);
                    ps.setString(2, father);
                    ps.setString(3, session_username);
                    ps.executeUpdate();
                    request.getRequestDispatcher("addFamily.jsp").forward(request,response);
                }
                conn.close();

            }catch (Exception e){
                System.out.println(e);
            }
        }

    }
}
