package com.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.stage.beans.Tache;

@Stateless
public class TacheDao {

	private static final String JPQL_SELECT_PAR_NOM = "SELECT u FROM Tache u WHERE u.nom =:nom";
    private static final String PARAM_NOM           = "nom";
	
	@PersistenceContext( unitName = "Tache_PU" )
    private EntityManager       em;
	
	public void creer( Tache tache ) throws DAOException {
        try {
        	em.persist( tache );
	    } catch ( Exception e ) {
	        throw new DAOException( e );
	    }
	 }
	
	public Tache trouver( String nom ) throws DAOException {
		Tache tache = null;
	    Query requete = em.createQuery( JPQL_SELECT_PAR_NOM );
        requete.setParameter( PARAM_NOM, nom );
        try {
        	tache = (Tache) requete.getSingleResult();
	     } catch ( NoResultException e ) {
	    	 return null;
	     } catch ( Exception e ) {
	    	 throw new DAOException( e );
	     }
	     return tache;
	 }
	
	
	
	 public static void deleteTache(String nom) {
	     Connection con = null;
	     PreparedStatement ps =null;
	     String sql = "delete from tache where nom = ?";
		 try {
			 con = DataBase.getConnection();
			 ps = con.prepareStatement(sql);
	         ps.setString(1, nom);
	         ps.executeUpdate(); 
	         System.out.println("JprojetDAO >> deleteTache ----------"+ nom);

	     } catch (SQLException e) {
	            System.out.println("JprojetDAO >> deleteTache----------- SQLException :(");
	            e.printStackTrace();
	     
	     } finally {
	          DataBase.close(con);
	     }
	}
	 
	 	 
	 
	

}

	

