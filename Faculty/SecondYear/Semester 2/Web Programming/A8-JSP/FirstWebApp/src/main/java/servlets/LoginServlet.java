package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String url= "jdbc:mysql://localhost:8889/transportation";
         String username = "Bianca";
         String password ="muzicuta123";


        try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection(url,username,password);

             String name= request.getParameter("txtName");
             String passowrd= request.getParameter("txtPwd");

             HttpSession session = request.getSession();

             PreparedStatement ps =conn.prepareStatement("SELECT username FROM User WHERE username=? and password=?");
             ps.setString(1,name);
             ps.setString(2,passowrd);
             ResultSet rs = ps.executeQuery();

             if(rs.next()){
                 session.setAttribute("username",name);
                 session.setAttribute("passowrd",passowrd);
                 response.sendRedirect("cities");
             }
             else{
                 PrintWriter out= response.getWriter();
                 out.println("<font color= red size= 10> Login Failed!! <br>");
                 out.println("<a href=index.jsp> Try Again!! </a>");
             }

         }catch(Exception e)
         {
             System.out.println(e);
         }

    }
}
