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
                    orderItem.OrderId = orderItemsSet.getInt("order_id");
                    orderItem.PepperID  = orderItemsSet.getInt("pepper_id");
                    orderItem.Quantity = orderItemsSet.getInt("quantity");
                    orderItem.Subtotal = orderItemsSet.getFloat("subtotal");
                    order.OrderItems.add(orderItem);
                }
            }
            
        } catch(SQLException ex) {
            
        }
        
        return null;
    }
    
}
