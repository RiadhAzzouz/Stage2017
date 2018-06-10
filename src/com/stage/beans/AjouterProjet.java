package com.stage.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.stage.dao.DataBase;
import com.stage.dao.ProjetDao;

@ManagedBean (name="AjouterProjet")
@ApplicationScoped
public class AjouterProjet implements Serializable {

	private static final long serialVersionUID = 1L;
	private Projet projet;
	
	@EJB
	private ProjetDao projetDao;
	
	public AjouterProjet() {
		projet = new Projet();
    }
	
	 public Projet getProjet() {
	    	return projet;
	 }
	 
	 public void inscrire() {
	        initialiserDateInscription();
	        projetDao.creer( projet );
	        FacesMessage message = new FacesMessage( "Succès de l'ajout !" );
	        FacesContext.getCurrentInstance().addMessage( null, message );
	        
	 }
	
	 private void initialiserDateInscription() {
	        Timestamp date = new Timestamp( System.currentTimeMillis() );
	        projet.setDateAjout( date );
	 }
	 
	 public List<Projet> getProjets() {
	    	PreparedStatement ps = null;
	    	Connection con = null;
	    	ResultSet rs = null;
	    	List <Projet> projets = new ArrayList<Projet>();
	    	
	    	try{
	    		String sql = "select * from projet";
	    		 con = DataBase.getConnection();
	    		 ps = con.prepareStatement(sql);
	    		 rs= ps.executeQuery(); 
	    		 while (rs.next())
	    		 {
	    			 Projet projet = new Projet();
	    			 projet.setId(rs.getLong("id"));
	    			 projet.setNom(rs.getString("nom"));
	    			 projet.setId_chef(rs.getLong("id_chef"));
	    			 projet.setPourcentage(rs.getInt("pourcentage"));
	    			 projet.setDateAjout(rs.getTimestamp("dateAjout"));
	    			 projets.add(projet);
	    		 } 
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally {
	            DataBase.close(con);
	        }
	  
	    	return projets;
	 }
	 
	 public String  delete(String nom) {
         ProjetDao.deleteProjet(nom);
         return "adminHomePage";
     }
	 
	 
}
