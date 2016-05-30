/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Pepper;
import Models.ShoppingCart;
import Models.ShoppingCartItem;
import Repositories.PeppersRepository;
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
//@WebServlet(urlPatterns = {"/Product"})
public class ProductDetailServlet extends HttpServlet {


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
        String pepperIdStr = request.getParameter("id"); 
        int pepperId = Integer.parseInt(pepperIdStr);
        PeppersRepository pr = new PeppersRepository();
        
        Pepper p = pr.getPepperById(pepperId);
        
        HttpSession session = request.getSession();
        ArrayList<Pepper> recentlyViewedItems = (ArrayList<Pepper>) session.getAttribute("recentItems");
        
        if(recentlyViewedItems == null){
            recentlyViewedItems = new ArrayList<Pepper>();
        } else {
            if(recentlyViewedItems.size() >= 5){
                recentlyViewedItems.remove(0);
            }
            
            //Search for existing pepper
            Pepper pepper = new Pepper();
            Boolean found = false;
            for(int i = 0; i < recentlyViewedItems.size(); i++){
                pepper = recentlyViewedItems.get(i);
                if(pepper.ID == pepperId){
                    found = true;
                    break;
                }
            }
            //remove it
            if(found) {
                recentlyViewedItems.remove(pepper);
            }
            
            recentlyViewedItems.add(p);
        }
        
        session.setAttribute("recentItems", recentlyViewedItems);
        
        request.setAttribute("pepper", p);
        request.getRequestDispatcher("productdetail.jsp").forward(request, response);
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
        boolean success = false;
        Pepper p = null;
        try {
            String pepperIdStr = request.getParameter("pepperId");
            String quantityStr = request.getParameter("quantity");
            int pepperId = Integer.parseInt(pepperIdStr);
            PeppersRepository pr = new PeppersRepository();
            p = pr.getPepperById(pepperId);
            
            //Throw error if quantity is not filled out
            if(quantityStr != null && !quantityStr.equals("")) {    
                int quantity = Integer.parseInt(quantityStr);
            
                ShoppingCartItem sci = new ShoppingCartItem(p, quantity);
            
                HttpSession session = request.getSession();
                ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingCart");
        
                if(sc == null){
                    sc = new ShoppingCart();
                }
                
                boolean exists = false;
                //update quantity if exists
                for(ShoppingCartItem item : sc.ShoppingCartItems) {
                    if(item.Pepper.ID == p.ID) {
                        item.Quantity += quantity;
                        exists = true;
                        break;
                    } 
                }
                
                //if item doesn't exists, add new item
                if(!exists) {
                    sc.ShoppingCartItems.add(sci);
                }
                
                session.setAttribute("shoppingCart", sc);
            
                success = true;
            } else {
                request.setAttribute("error", "You need to enter a quantity");
            }
            
        } catch(Exception ex) {    
            
            request.setAttribute("error", "Oops! Error has occured!");
        }
        
        if(success){
            response.sendRedirect(response.encodeRedirectURL("/shoppingcart"));
        } else { 
            request.setAttribute("pepper", p);
            request.getRequestDispatcher("productdetail.jsp").forward(request, response);
        }
        
    }
}
