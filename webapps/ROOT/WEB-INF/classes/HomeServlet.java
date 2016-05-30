import Models.Pepper;
import Repositories.PeppersRepository;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        PeppersRepository pr = new PeppersRepository();
        ArrayList<Pepper> peppers = pr.getAllPeppers();
        
        request.setAttribute("peppers", peppers);
        
        HttpSession session = request.getSession();
        ArrayList<Pepper> recentlyViewedItems = (ArrayList<Pepper>) session.getAttribute("recentItems");
        
        request.setAttribute("recentItems", recentlyViewedItems);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }  // end doGet(
}
