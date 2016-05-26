import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ArrayList<String>> pepperList = new ArrayList<ArrayList<String>>();

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
            out.println("<p>HI</p>");

            String servername = "sylvester-mccoy-v3.ics.uci.edu";
            String db="inf124grp13";
            String username = "inf124grp13";
            String password = "4a=eDuVu";

            con = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + db, username, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM peppers");
            while (rs.next()){
                ArrayList<String> pepper = new ArrayList<String>();
                pepper.add(rs.getString("url"));
                pepper.add(rs.getString("image_url"));
                pepper.add(rs.getString("pepper_name"));
                pepper.add(rs.getString("pepper_type"));
                pepper.add(rs.getString("spicy_creative"));
                pepper.add(rs.getString("price"));
                pepperList.add(pepper);
            }
            request.setAttribute("peppers", pepperList);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }  // end doGet(
}
