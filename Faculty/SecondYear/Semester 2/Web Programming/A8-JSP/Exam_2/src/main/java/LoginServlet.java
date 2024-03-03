import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url= "jdbc:mysql://localhost:8889/exam_2";
        String username = "Bianca";
        String password ="muzicuta123";


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,username,password);

            String name= request.getParameter("txtName");
            String mother = request.getParameter("mother");
            String father = request.getParameter("father");

            HttpSession session = request.getSession();

            PreparedStatement ps =conn.prepareStatement("SELECT mother,father FROM FamilyRelations WHERE username=? ");
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                if (mother.equals(rs.getString("mother")) || father.equals(rs.getString("father"))) {
                    session.setAttribute("username", name);
                    session.setAttribute("mother", mother);
                    session.setAttribute("father", father);
                    request.getRequestDispatcher("addFamily.jsp").forward(request, response);
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<font color= red size= 10> Login Failed!! <br>");
                    out.println("<a href=index.jsp> Try Again!! </a>");
                }
            }
             }catch(Exception e)
        {
            System.out.println(e);
        }

    }


}
