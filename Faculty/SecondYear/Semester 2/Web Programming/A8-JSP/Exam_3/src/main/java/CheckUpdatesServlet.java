import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CheckUpdatesServlet", value = "/checkUpdatesServlet")
public class CheckUpdatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if there are new updates
        HttpSession session = request.getSession();
        String url = "jdbc:mysql://localhost:8889/exam_3";
        String username = "Bianca";
        String password = "muzicuta123";

        try {
            // First we check if the number of elements that are now present in the database is the same with the number
            // of elements that the user had in the first place, or the last time the check was done
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            String currentNumElementsQuery1 = "SELECT COUNT(*) AS numElements FROM Posts";
            PreparedStatement currentNumElementsStmt1 = conn.prepareStatement(currentNumElementsQuery1);
            ResultSet currentNumElementsResult1 = currentNumElementsStmt1.executeQuery();

            int currentNumElements1 = 0;
            if (currentNumElementsResult1.next()) {
                currentNumElements1 = currentNumElementsResult1.getInt("numElements");
            }

            // if those number are not the same , we get everything from the database , sort it descending and show the user the last
            // post that was added
            Integer result1 = (int) session.getAttribute("nrElements");
            if(currentNumElements1 != result1 ) {
                String currentNumElementsQuery = "SELECT * FROM Posts ORDER BY id DESC LIMIT 1";
                PreparedStatement currentNumElementsStmt = conn.prepareStatement(currentNumElementsQuery);
                ResultSet currentNumElementsResult = currentNumElementsStmt.executeQuery();

                if (currentNumElementsResult.next()) {
                    String user = currentNumElementsResult.getString("user");
                    int topic = currentNumElementsResult.getInt("topicid");
                    String text = currentNumElementsResult.getString("text");
                    Date date = currentNumElementsResult.getDate("date");

                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.write("{\"user\": \"" + user + "\", \"topic\": " + topic + ", \"text\": \"" + text + "\", \"date\": \"" + date + "\"}");
                } else {
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    out.write("");
                }

                conn.close(); // Close the database connection
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
