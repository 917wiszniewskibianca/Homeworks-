package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RemoveLastCity", value = "/removeLastCity")
public class RemoveLastCity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of selected cities from session attribute
        HttpSession session = request.getSession();
        String session_username = (String)session.getAttribute("username");
        if(session_username == null)
        { request.getRequestDispatcher("").forward(request,response);}
        else {
            ArrayList<String> selectedCities = (ArrayList<String>) session.getAttribute("selectedCities");

            if (selectedCities != null && !selectedCities.isEmpty()) {
                // Remove the last city from the list
                selectedCities.remove(selectedCities.size() - 1);

                // Update the session attribute with the modified list
                session.setAttribute("selectedCities", selectedCities);

                // Return a successful response
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // Return a response indicating no city to remove
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
