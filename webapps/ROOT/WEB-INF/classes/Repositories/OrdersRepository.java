/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Models.Order;
import Models.OrderItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author peter
 */
public class OrdersRepository {
    
    /**
     * Gets and order by Id
     * @param id
     * @return 
     */
    public Order getOrderById(int id) {
        try {
            Order order = new Order();
            Connection conn = DatabaseContext.getDbConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM orders where ID = %s", id));
            
            while (rs.next()){ 

                order.ID = rs.getInt("ID");
                order.CreditCardNumber = rs.getLong("cc_number");
                order.FirstName = rs.getString("first_name");
		order.LastName = rs.getString("last_name");
                order.StreetAddress = rs.getString("street_addr");
                order.PhoneNumber = rs.getLong("phone_num");
                order.State = rs.getString("state");
                order.Zip = rs.getInt("zip");
                order.ShippingSpeed = rs.getInt("ship_speed");
                
                ResultSet orderItemsSet = stmt.executeQuery(String.format("SELECT * FROM orderitems where order_id = %s", order.ID ));
                order.OrderItems = new ArrayList<>();
                while(orderItemsSet.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.ID = orderItemsSet.getInt("ID");
                    orderItem.OrderID = orderItemsSet.getInt("order_id");
                    orderItem.PepperID  = orderItemsSet.getInt("pepper_id");
                    orderItem.Quantity = orderItemsSet.getInt("quantity");
                    orderItem.Subtotal = orderItemsSet.getFloat("subtotal");
                    order.OrderItems.add(orderItem);
                }
            }
            
            stmt.close();
        } catch(SQLException ex) {
            
        }
        
        return null;
    }
    
    /**
     * Creates and order in the database
     * @param order
     * @return 
     */
    public int createOrder(Order order) {
        try {
            Connection conn = DatabaseContext.getDbConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO orders (first_name, last_name, cc_number, street_addr, phone_num, zip, state, ship_speed) VALUES (%1$s, %2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s)", 
                                            order.FirstName, order.LastName, order.CreditCardNumber, order.StreetAddress, order.PhoneNumber, order.Zip, order.State, order.ShippingSpeed), Statement.RETURN_GENERATED_KEYS);
            
            //Get order id of the inserted order.
            int orderId = -1;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
               orderId = rs.getInt(1);
            }

            for(OrderItem orderItem : order.OrderItems) {
               stmt.executeUpdate(String.format("INSERT INTO orderitems (order_id, pepper_id, quantity, subtotal) VALUES (%1$s, %2$s,%3$s,%4$s)", 
                                            orderId, orderItem.PepperID, orderItem.Quantity, orderItem.Subtotal)); 
            }
            
            stmt.close();
        } catch(SQLException ex) { };
        
        return -1;
    }
    
}
