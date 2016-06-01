import Models.Order;
import Repositories.OrdersRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Long;
import java.lang.Integer;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String id = request.getParameter("orderID");
        int orderId = Integer.parseInt(id);
        OrdersRepository orderRepo = new OrdersRepository();
        Order order = orderRepo.getOrderById(orderId);
        
        response.setContentType("text/html;charset=UTF-8");
        out.println(id);
        out.println(order.Error);
        
        Order testOrder = new Order();
        testOrder.FirstName = "Test";
        
        request.setAttribute("order", order);
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    } //end doGet
}
