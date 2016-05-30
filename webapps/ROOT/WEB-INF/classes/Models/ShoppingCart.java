/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author peter
 */
public class ShoppingCart {
    public ArrayList<ShoppingCartItem> ShoppingCartItems;
    
    public ShoppingCart() {
        ShoppingCartItems = new ArrayList<ShoppingCartItem>();
    }
    
    public float getTotal() {
        float total = 0;
        for(ShoppingCartItem sci : ShoppingCartItems){
            total += (sci.Pepper.Price * sci.Quantity);
        }
        
        return total;
    }
}
