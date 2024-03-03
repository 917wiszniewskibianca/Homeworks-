import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hi from get ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user= (String) session.getAttribute("username");

        if(user == null) {

            request.getRequestDispatcher("").forward(request,response);
        }
        else {

            String topic = request.getParameter("txtTopic");
            String text = request.getParameter("txtText");
            String idd = request.getParameter("postId");
            Integer ida = Integer.parseInt(idd);

            java.util.Date currentDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());

            String url = "jdbc:mysql://localhost:8889/exam_3";
            String username = "Bianca";
            String password = "muzicuta123";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);

                PreparedStatement checkTopic = conn.prepareStatement("SELECT id FROM Topics WHERE topicname = ?");
                checkTopic.setString(1, topic);
                ResultSet result = checkTopic.executeQuery();


                if (!result.next()) {
                    PreparedStatement addTopic = conn.prepareStatement("INSERT INTO Topics (topicname) VALUES (?)");
                    addTopic.setString(1, topic);
                    addTopic.executeUpdate();

                    PreparedStatement getId = conn.prepareStatement("SELECT LAST_INSERT_ID() AS added_topic_id");
                    ResultSet ress = getId.executeQuery();

                    if (ress.next()) {
                        Integer added_topic_id = ress.getInt("added_topic_id");

                        PreparedStatement ps1 = conn.prepareStatement("UPDATE Posts SET user = ?, topicid = ?, text = ?, date = ? WHERE id = ?");
                        ps1.setString(1, user);
                        ps1.setInt(2, added_topic_id);
                        ps1.setString(3, text);
                        ps1.setDate(4, date);
                        ps1.setInt(5, ida);
                        ps1.executeUpdate();

                        response.sendRedirect("PostsServlet");
                    }
                } else {
                    Integer topic_id = result.getInt("id");
                    PreparedStatement ps = conn.prepareStatement("UPDATE Posts SET user = ?, topicid = ?, text = ?, date = ? WHERE id = ?");
                    ps.setString(1, user);
                    ps.setInt(2, topic_id);
                    ps.setString(3, text);
                    ps.setDate(4, date);
                    ps.setInt(5, ida);
                    System.out.println(ida);
                    ps.executeUpdate();
                    response.sendRedirect("PostsServlet");
                }

                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
