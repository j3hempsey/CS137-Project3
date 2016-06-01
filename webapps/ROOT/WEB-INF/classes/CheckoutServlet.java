/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Pepper;
import Models.Order;
import Models.OrderItem;
import Models.ShoppingCart;
import Models.ShoppingCartItem;
import Models.UpdateShoppingCartResult;
import Repositories.PeppersRepository;
import Repositories.OrdersRepository;
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

/**
 *
 * @author peter
 */
//@WebServlet(urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

        request.setAttribute("shoppingCart", shoppingCart);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: IMPLEMENT CHECKING OUT
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        OrdersRepository orderDb = new OrdersRepository();
        Order newOrder = new Order();
        int orderID = 0;

        newOrder.FirstName = request.getParameter("first_name");
        newOrder.LastName = request.getParameter("last_name");
        newOrder.CreditCardNumber = Long.parseLong(request.getParameter("credit_card"), 10);
        newOrder.StreetAddress = request.getParameter("address");
        newOrder.State = request.getParameter("state");
        newOrder.PhoneNumber = Long.parseLong(request.getParameter("phone"), 10);
        newOrder.Zip = Integer.parseInt(request.getParameter("zip_code"), 10);
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        ArrayList<OrderItem> itemList = new ArrayList<OrderItem>();
        OrderItem temp = new OrderItem();
        for (ShoppingCartItem i : shoppingCart.ShoppingCartItems)
        {
            temp.ID = 0;
            temp.OrderID = 0;
            temp.PepperID = i.Pepper.ID;
            temp.Quantity = i.Quantity;
            temp.Subtotal = i.Pepper.Price * i.Quantity;
            itemList.add(temp);
        }
        newOrder.OrderItems = itemList;
        String shipString = request.getParameter("ship");
        if (shipString.equals("one-day")) {
            newOrder.ShippingSpeed = 1;
        } else if (shipString.equals("two-day")) {
            newOrder.ShippingSpeed = 2;
        } else {
            newOrder.ShippingSpeed = 3;
        }
        try {
          orderID = orderDb.createOrder(newOrder);
        } catch (Exception e) {
          out.println(e);
        }
        //forward to confirmation page
        response.sendRedirect("/confirmation?orderID=" + orderID);
    }
}
