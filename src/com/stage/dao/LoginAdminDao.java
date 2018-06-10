package com.stage.dao;
import java.sql.*;
  
public class LoginAdminDao {      
    
	public static boolean login(String nom, String mot_de_passe) {
		Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataBase.getConnection();
            ps = con.prepareStatement(
                    "select nom, mot_de_passe from administrateur where nom= ? and mot_de_passe= ? ");
           
            ps.setString(1, nom);
            ps.setString(2, mot_de_passe);
            
  
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) // found
            {
                System.out.println(rs.getString("nom"));
                return true;
            }
            
            else{
            	return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            DataBase.close(con);
        }
    }
}