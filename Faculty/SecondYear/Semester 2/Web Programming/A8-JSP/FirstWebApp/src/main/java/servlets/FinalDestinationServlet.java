package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FinalDestinationServlet", value = "/FinalDestinationServlet")
public class FinalDestinationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String session_username = (String)session.getAttribute("username");
        if(session_username == null)
        { request.getRequestDispatcher("").forward(request,response);}
        else {
            ArrayList<String> selectedCities = (ArrayList<String>) session.getAttribute("selectedCities");
            request.setAttribute("selectedCities", selectedCities);// Store the cities in the request attribute
            request.getRequestDispatcher("finalDestination.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
