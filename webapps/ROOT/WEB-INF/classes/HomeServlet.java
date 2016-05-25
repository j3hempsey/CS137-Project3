import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//@WebServlet("/helloworld")
public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet helloworld</title>");
            out.println("</head>");
            out.println("<body>");
            out.println ("<h1>Hello World</h1><br/>");
            out.println("<h1>Servlet helloworld at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }  // end doGet()
    }  // end helloworld
}
