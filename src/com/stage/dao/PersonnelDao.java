package com.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.stage.beans.Personnel;

@Stateless
public class PersonnelDao {
	
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Personnel u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
    private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Personnel u WHERE u.id=:id";
    private static final String PARAM_ID           = "id";
   
	
	@PersistenceContext( unitName = "Personnel_PU" )
     private EntityManager       em;

	
	public void creer( Personnel personnel ) throws DAOException {
		 try {
			 em.persist( personnel );
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	}
	
	public static void deletePersonnel(String email) {
	     Connection con = null;
	     PreparedStatement ps =null;
	     String sql = "delete from personnel where email = ?";
		 try {
			 con = DataBase.getConnection();
			 ps = con.prepareStatement(sql);
	         ps.setString(1, email);
	         ps.executeUpdate(); 
	         System.out.println("JpersonnelDAO >> deleteUser ----------"+ email);

	     } catch (SQLException e) {
	            System.out.println("JpersonnelDAO >> deleteUser----------- SQLException :(");
	            e.printStackTrace();
	     
	     } finally {
	          DataBase.close(con);
	     }
	}
	
	
	 public Personnel trouver( String email ) throws DAOException {
		 Personnel personnel = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
         requete.setParameter( PARAM_EMAIL, email );
         	try {
         		personnel = (Personnel) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return personnel ;
	 }
	 
	 public Personnel trouver( long id ) throws DAOException {
		 Personnel personnel = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_ID );
         requete.setParameter( PARAM_ID, id );
         	try {
         		personnel = (Personnel) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return personnel ;
	 }
	 
	 
}
