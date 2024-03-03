import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "FindFamilyLineServlet", value = "/FindFamilyServlet")
public class FindFamilyLineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pt mama
        HttpSession session = request.getSession();
        String session_username = (String) session.getAttribute("username");

        if (session_username == null) {

            request.getRequestDispatcher("").forward(request, response);
        } else {

            String url = "jdbc:mysql://localhost:8889/exam_2";
            String username = "Bianca";
            String password = "muzicuta123";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement ps1 = conn.prepareStatement("Select mother from FamilyRelations where username = ?");
                ps1.setString(1,session_username);
                ResultSet rs= ps1.executeQuery();
                ArrayList<String> motherLine = new ArrayList<>();
                while(rs.next()){
                    motherLine.add(rs.getString("mother"));
                    session_username=rs.getString("mother");
                    ps1.setString(1,session_username);
                    rs = ps1.executeQuery();

                }
                request.setAttribute("motherLine",motherLine);
                request.getRequestDispatcher("showMotherLine.jsp").forward(request,response);
                conn.close();

            }catch (Exception e){
                System.out.println(e);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //pt tata
        HttpSession session = request.getSession();
        String session_username = (String) session.getAttribute("username");

        if (session_username == null) {

            request.getRequestDispatcher("").forward(request, response);
        } else {

            String url = "jdbc:mysql://localhost:8889/exam_2";
            String username = "Bianca";
            String password = "muzicuta123";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement ps1 = conn.prepareStatement("Select father from FamilyRelations where username = ?");
                ps1.setString(1,session_username);
                ResultSet rs= ps1.executeQuery();
                ArrayList<String> fatherLine = new ArrayList<>();
                while(rs.next()){
                    fatherLine.add(rs.getString("father"));
                    session_username=rs.getString("father");
                    ps1.setString(1,session_username);
                    rs = ps1.executeQuery();

                }
                request.setAttribute("fatherLine",fatherLine);
                request.getRequestDispatcher("showFatherLine.jsp").forward(request,response);
                conn.close();

            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
}
