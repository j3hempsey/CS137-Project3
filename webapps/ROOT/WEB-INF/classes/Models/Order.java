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
public class Order {
    public int ID;
    public String FirstName;
    public String LastName;
    public long CreditCardNumber;
    public String StreetAddress;
    public String State;
    public long PhoneNumber;
    public int Zip;
    public ArrayList<OrderItem> OrderItems;
    public int ShippingSpeed;
    public String Error;
    
    
    public float getTotal() {
        float total = 0;
        for (OrderItem oi : OrderItems) {
            total += (oi.Subtotal * oi.Quantity);
        }
        return total;
    }
}
