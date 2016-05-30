/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Repositories.PeppersRepository;

/**
 *
 * @author peter
 */
public class OrderItem {
    public int ID;
    public int OrderID;
    public int PepperID;
    public int Quantity;
    public float Subtotal;
    
    public Pepper getPepper() {
        try {
            PeppersRepository pr = new PeppersRepository();
            Pepper p = pr.getPepperById(this.PepperID);
            return p;
        } catch(Exception ex){}
        
        return null;
    }
}
