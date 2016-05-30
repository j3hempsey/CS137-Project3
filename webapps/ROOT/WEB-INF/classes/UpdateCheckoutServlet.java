/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.ShoppingCart;
import Models.ShoppingCartItem;
import Models.UpdateShoppingCartResult;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
//@WebServlet(urlPatterns = {"/UpdateCheckoutServlet"})
public class UpdateCheckoutServlet extends HttpServlet {

 
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
        
        UpdateShoppingCartResult updateShoppingCartResult = new UpdateShoppingCartResult();
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        
        try {
            String pepperIdStr = request.getParameter("pepperId");
            String updateType = request.getParameter("updateType");
            int pepperId = Integer.parseInt(pepperIdStr);
            
            if(updateType.equals("quantity")) {
                String quantityStr = request.getParameter("quantity");
                int quantity = Integer.parseInt(quantityStr);
        
                float subtotal = 0;
                for(ShoppingCartItem shoppingCartItem : shoppingCart.ShoppingCartItems) {
                    if(shoppingCartItem.Pepper.ID == pepperId) {
                        shoppingCartItem.Quantity = quantity;
                        subtotal = shoppingCartItem.Quantity * shoppingCartItem.Pepper.Price;
                        break;
                    }
                }
        
                updateShoppingCartResult.Subtotal = String.format("%.2f", subtotal);
                updateShoppingCartResult.Total = String.format("%.2f", shoppingCart.getTotal());
                updateShoppingCartResult.Success = true;
                
            } else if(updateType.equals("remove")) {
                ArrayList<ShoppingCartItem> shoppingCartItems = shoppingCart.ShoppingCartItems;
                
                //Search for existing shopping cart item
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                for(int i = 0; i < shoppingCartItems.size(); i++){
                    shoppingCartItem = shoppingCartItems.get(i);
                    if(shoppingCartItem.Pepper.ID == pepperId){
                        break;
                    }
                }
                //remove it
                shoppingCart.ShoppingCartItems.remove(shoppingCartItem);
                
                updateShoppingCartResult.Total = String.format("%.2f", shoppingCart.getTotal());
                updateShoppingCartResult.Success = true;
            } 
            
        } catch(Exception ex) {
            updateShoppingCartResult.Success = false;
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            updateShoppingCartResult.ErrorMessage = exceptionAsString;
        }
        
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(updateShoppingCartResult));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
