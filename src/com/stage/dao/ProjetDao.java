package com.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.stage.beans.Projet;

@Stateless
public class ProjetDao {

	private static final String JPQL_SELECT_PAR_NOM = "SELECT u FROM Projet u WHERE u.nom =:nom";
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Projet u WHERE u.id =:id";
    private static final String PARAM_NOM           = "nom";
    private static final String PARAM_ID           = "id";
	
	@PersistenceContext( unitName = "Projet_PU" )
    private EntityManager       em;
	
	public void creer( Projet projet ) throws DAOException {
        try {
        	em.persist( projet );
	    } catch ( Exception e ) {
	        throw new DAOException( e );
	    }
	 }
	
	public Projet trouver( String nom ) throws DAOException {
		Projet projet = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_NOM );
         requete.setParameter( PARAM_NOM, nom );
        try {
       	 projet = (Projet) requete.getSingleResult();
	     } catch ( NoResultException e ) {
	    	 return null;
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	     return projet;
	 }
	
	public Projet trouver( long id ) throws DAOException {
		 Projet projet = null;
	     Query requete = em.createQuery( JPQL_SELECT_PAR_ID );
         requete.setParameter( PARAM_ID, id );
        try {
       	 projet = (Projet) requete.getSingleResult();
	     } catch ( NoResultException e ) {
	    	 return null;
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	     return projet;
	 }
	
	 public static void deleteProjet(String nom) {
	     Connection con = null;
	     PreparedStatement ps =null;
	     String sql = "delete from projet where nom = ?";
		 try {
			 con = DataBase.getConnection();
			 ps = con.prepareStatement(sql);
	         ps.setString(1, nom);
	         ps.executeUpdate(); 
	         System.out.println("JprojetDAO >> deleteProjet ----------"+ nom);

	     } catch (SQLException e) {
	            System.out.println("JprojetDAO >> deleteProjet----------- SQLException :(");
	            e.printStackTrace();
	     
	     } finally {
	          DataBase.close(con);
	     }
	}

	
}
