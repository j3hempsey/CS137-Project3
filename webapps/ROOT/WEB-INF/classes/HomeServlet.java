import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//@WebServlet("/helloworld")
//Class.forName ("com.mysql.jdbc.Driver");
public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            Class.forName("com.mysql.jdbc.Driver");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet helloworld</title>");
            out.println("</head>");
            out.println("<body>");
            out.println ("<h1>Hello World!!</h1><br/>");
            //out.println("<h1>Servlet helloworld at " + request.getContextPath() + "</h1>");

            String servername = "sylvester-mccoy-v3.ics.uci.edu";
            String db="inf124grp13";
            String username = "inf124grp13";
            String password = "4a=eDuVu";

            con = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + db, username, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM peppers");
            out.println("<p>HI</p>");
            while (rs.next()){
                out.println("<p>" + rs.getString("pepper_name")+ "</p>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }  // end doGet(
}
