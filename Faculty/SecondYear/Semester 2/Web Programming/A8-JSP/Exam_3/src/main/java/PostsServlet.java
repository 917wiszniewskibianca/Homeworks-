import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "PostsServlet", value = "/PostsServlet")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session= request.getSession();
          String session_username= (String) session.getAttribute("username");

        if(session_username == null) {

            request.getRequestDispatcher("").forward(request,response);
        } else{
              String url="jdbc:mysql://localhost:8889/exam_3";
              String username = "Bianca";
              String password = "muzicuta123";

              try {
                  Class.forName("com.mysql.jdbc.Driver");
                  Connection conn = DriverManager.getConnection(url,username,password);
                  PreparedStatement ps = conn.prepareStatement("SELECT p.id, p.user, t.topicname, p.text, p.date  FROM Posts p JOIN Topics t ON p.topicid = t.id");

                  String currentNumElementsQuery = "SELECT COUNT(*) AS numElements FROM Posts";
                  PreparedStatement currentNumElementsStmt = conn.prepareStatement(currentNumElementsQuery);
                  ResultSet currentNumElementsResult = currentNumElementsStmt.executeQuery();

                  int currentNumElements = 0;
                  if (currentNumElementsResult.next()) {
                      currentNumElements = currentNumElementsResult.getInt("numElements");
                  }
                  session.setAttribute("nrElements",currentNumElements);
                  ResultSet result = ps.executeQuery();
                  request.setAttribute("posts",result);
                  request.getRequestDispatcher("posts.jsp").forward(request,response);

              } catch (Exception e) {
                  System.out.println(e);
              }

          }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        if(user == null){
            response.sendRedirect("");
        }
        else {

            String topic = request.getParameter("addTopic");
            String text = request.getParameter("addText");

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
                        PreparedStatement addPost = conn.prepareStatement("INSERT INTO Posts (user, topicid, text, date) VALUES (?, ?, ?, ?)");
                        addPost.setString(1, user);
                        addPost.setInt(2, added_topic_id);
                        addPost.setString(3, text);
                        addPost.setDate(4, date);
                        addPost.executeUpdate();

                        String currentNumElementsQuery = "SELECT COUNT(*) AS numElements FROM Posts";
                        PreparedStatement currentNumElementsStmt = conn.prepareStatement(currentNumElementsQuery);
                        ResultSet currentNumElementsResult = currentNumElementsStmt.executeQuery();

                        int currentNumElements = 0;
                        if (currentNumElementsResult.next()) {
                            currentNumElements = currentNumElementsResult.getInt("numElements");
                        }
                        session.setAttribute("nrElements",currentNumElements);
                        doGet(request,response);
                    }
                } else {
                    Integer topic_id = result.getInt("id");
                    PreparedStatement addPost = conn.prepareStatement("INSERT INTO Posts (user, topicid, text, date) VALUES (?, ?, ?, ?)");
                    addPost.setString(1, user);
                    addPost.setInt(2, topic_id);
                    addPost.setString(3, text);
                    addPost.setDate(4, date);
                    addPost.executeUpdate();
                    addPost.executeUpdate();
                    String currentNumElementsQuery = "SELECT COUNT(*) AS numElements FROM Posts";
                    PreparedStatement currentNumElementsStmt = conn.prepareStatement(currentNumElementsQuery);
                    ResultSet currentNumElementsResult = currentNumElementsStmt.executeQuery();

                    int currentNumElements = 0;
                    if (currentNumElementsResult.next()) {
                        currentNumElements = currentNumElementsResult.getInt("numElements");
                    }
                    session.setAttribute("nrElements",currentNumElements);
                    doGet(request, response);
                }

                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}



