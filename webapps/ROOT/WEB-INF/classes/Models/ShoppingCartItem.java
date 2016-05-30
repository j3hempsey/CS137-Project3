/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author peter
 */
public class ShoppingCartItem {
        public ShoppingCartItem(){
            
        }
        public ShoppingCartItem(Pepper p, int quantity){
            this.Pepper = p;
            this.Quantity = quantity;
        }
        
        public Pepper Pepper;
        public int Quantity;
    }