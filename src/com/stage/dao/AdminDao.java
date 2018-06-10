package com.stage.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.*;
import com.stage.beans.Administrateur;

@Stateless
public class AdminDao {
	
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Administrateur u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
     
	@PersistenceContext( unitName = "Admin_PU" )
    private EntityManager       em;
	
	 public void creer( Administrateur administrateur ) throws DAOException {
        try {
        	em.persist( administrateur );
	    } catch ( Exception e ) {
	        throw new DAOException( e );
	    }
	 }
	 
	 
	 
	 public Administrateur trouver( String email ) throws DAOException {
		 Administrateur administrateur = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
         requete.setParameter( PARAM_EMAIL, email );
         try {
        	 administrateur = (Administrateur) requete.getSingleResult();
	     } catch ( NoResultException e ) {
	    	 return null;
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	     return administrateur;
	 }
	
	 public static void deleteAdmin(String email) {
	     Connection con = null;
	     PreparedStatement ps =null;
	     String sql = "delete from administrateur where email = ?";
		 try {
			 con = DataBase.getConnection();
			 ps = con.prepareStatement(sql);
	         ps.setString(1, email);
	         ps.executeUpdate(); 
	         System.out.println("JadminDAO >> deleteUser ----------"+ email);

	     } catch (SQLException e) {
	            System.out.println("JadminDAO >> deleteUser----------- SQLException :(");
	            e.printStackTrace();
	     
	     } finally {
	          DataBase.close(con);
	     }
	}
	
}
