import Models.Pepper;
import Repositories.PeppersRepository;
import Repositories.DatabaseContext;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;


public class PepperServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Create database connection.
		String pepperID = request.getParameter("id"); //Get the id of the pepper.
		try
		{
			Connection dbConn = DatabaseContext.getDbConnection();
			Statement stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM peppers WHERE ID=" + pepperID);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
}
