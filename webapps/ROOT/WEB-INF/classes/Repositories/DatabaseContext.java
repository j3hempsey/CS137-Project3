/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author peter
 */
public class DatabaseContext {
    public static Connection getDbConnection() {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String servername = "sylvester-mccoy-v3.ics.uci.edu";
            String db="inf124grp13";
            String username = "inf124grp13";
            String password = "4a=eDuVu";

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + db, username, password);
        return conn;
        } catch(Exception ex) {
            
        }
        
        return null;
    }
}
