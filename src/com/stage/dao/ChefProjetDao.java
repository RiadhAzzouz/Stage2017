package com.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.stage.beans.ChefProjet;

@Stateless
public class ChefProjetDao {
	
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM ChefProjet u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
    private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM ChefProjet u WHERE u.id=:id";
    private static final String PARAM_ID           = "id";
    

	
	@PersistenceContext( unitName = "Chef_PU" )
    private EntityManager       em;
	
	 public void creer( ChefProjet chefprojet ) throws DAOException {
		 try {
			 em.persist( chefprojet );
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	 }
	 
	 
	 
	 public ChefProjet trouver( String email ) throws DAOException {
		 ChefProjet chefprojet = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
         requete.setParameter( PARAM_EMAIL, email );
         	try {
         		chefprojet = (ChefProjet) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return chefprojet ;
	 }
	 
	 public ChefProjet trouver( long id ) throws DAOException {
		 ChefProjet chefprojet = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_ID );
         requete.setParameter( PARAM_ID, id );
         	try {
         		chefprojet = (ChefProjet) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return chefprojet ;
	 }
	 
	 public static void deleteChef(String email) {
	     Connection con = null;
	     PreparedStatement ps =null;
	     String sql = "delete from chefprojet where email = ?";
		 try {
			 con = DataBase.getConnection();
			 ps = con.prepareStatement(sql);
	         ps.setString(1, email);
	         ps.executeUpdate(); 
	         System.out.println("JchefDAO >> deleteUser ----------"+ email);

	     } catch (SQLException e) {
	            System.out.println("JchefDAO >> deleteUser----------- SQLException :(");
	            e.printStackTrace();
	     
	     } finally {
	          DataBase.close(con);
	     }
	}
	

}
