/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Models.Pepper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author peter
 */
public class PeppersRepository {
    
    /**
     * Gets all peppers from DB
     * @return 
     */
    public ArrayList<Pepper> getAllPeppers() {
        
        try {
            ArrayList<Pepper> peppers = new ArrayList<Pepper>();
            Connection conn = DatabaseContext.getDbConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM peppers");
            while (rs.next()){
                Pepper pepper = new Pepper();
                pepper.ID = rs.getInt("ID");
                pepper.Url = rs.getString("url");
                pepper.ImageUrl = rs.getString("image_url");
				pepper.ImageUrlAlt = rs.getString("image_url_alt");
                pepper.PepperName = rs.getString("pepper_name");
                pepper.PepperType = rs.getString("pepper_type");
                pepper.SpicyCreative = rs.getString("spicy_creative");
                pepper.SpicyLevel = rs.getString("spicy_level");
                pepper.Price = rs.getFloat("price");
                pepper.Description = rs.getString("description");
                
                peppers.add(pepper);
            }
            
            stmt.close();
            return peppers;
            
        } catch(Exception ex) {
            
        }
        
        return null;
    }
    
    /**
     * Gets all peppers from DB
     * @return 
     */
    public Pepper getPepperById(int id) {
        
        try {
            Pepper pepper = new Pepper();
            Connection conn = DatabaseContext.getDbConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM peppers where ID = '%1$s'", id));
            if (rs.next()){
                pepper.ID = rs.getInt("ID");
                pepper.Url = rs.getString("url");
                pepper.ImageUrl = rs.getString("image_url");
                pepper.PepperName = rs.getString("pepper_name");
                pepper.PepperType = rs.getString("pepper_type");
                pepper.SpicyCreative = rs.getString("spicy_creative");
                pepper.SpicyLevel = rs.getString("spicy_level");
                pepper.Price = rs.getFloat("price");
                pepper.Description = rs.getString("description");
            }
            
            stmt.close();
            return pepper;
            
        } catch(Exception ex) {
            
        }
        
        return null;
    }
}
