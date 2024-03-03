import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateOrderServlet", value = "/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String session_username = (String) session.getAttribute("username");

        if (session_username == null) {
            request.getRequestDispatcher("").forward(request, response);
        } else {
            String product = request.getParameter("addProduct");
            String paramValue = request.getParameter("addQuantity");
            Integer quantity = Integer.parseInt(paramValue);

            Map<String, Integer> dictionary = (Map<String, Integer>) session.getAttribute("orders");
            if (dictionary == null) {
                dictionary = new HashMap<>();
                dictionary.put(product,quantity);
            }
            else {
                int existingValue = dictionary.getOrDefault(product, 0);

                // Perform the addition
                int updatedValue = existingValue + quantity;
                dictionary.put(product, updatedValue);
            }
            session.setAttribute("orders", dictionary);
            request.getRequestDispatcher("products.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> dictionary = (Map<String, Integer>) session.getAttribute("orders");

        if (dictionary != null) {
            String url = "jdbc:mysql://localhost:8889/exam_1";
            String username = "Bianca";
            String password = "muzicuta123";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                String user = (String) session.getAttribute("username");
                String insertQuery = "INSERT INTO Orders (user,productid, quantity) VALUES (?,?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertQuery);
                ps.setString(1,user);

                for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                    String name = entry.getKey();
                    int quantity = entry.getValue();

                    PreparedStatement getProductId = conn.prepareStatement("Select id FROM Products where name= ?");
                    getProductId.setString(1,name);
                    ResultSet result1 = getProductId.executeQuery();
                    if (result1.next()) {
                        int productId = result1.getInt("id");
                        ps.setInt(2, productId);
                        ps.setInt(3, quantity);
                        ps.executeUpdate();
                    }

                }

                conn.close();
                session.removeAttribute("orders");  // Clear the dictionary from the session

                response.getWriter().println("Successfully inserted orders into the database.");
                //request.getRequestDispatcher("products.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while inserting orders into the database.");
            }
        } else {
            response.getWriter().println("No orders found to insert.");
        }

    }
}
