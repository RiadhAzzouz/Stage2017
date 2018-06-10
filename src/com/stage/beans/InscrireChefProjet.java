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
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.stage.dao.ChefProjetDao;
import com.stage.dao.DataBase;

@ManagedBean (name="InscrireChefProjet")
@ApplicationScoped
public class InscrireChefProjet implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private ChefProjet chefprojet;
	
	
	@EJB
    private ChefProjetDao    chefprojetDao;

    
    public InscrireChefProjet() {
        chefprojet = new ChefProjet();
    }

    public String inscrire() {
    	chefprojet.setAccepted(false);
    	initialiserDateInscription();
        chefprojetDao.creer( chefprojet );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "loginChef";
    }
    
    public List<ChefProjet> getChefProjets() {
    	PreparedStatement ps = null;
    	Connection con = null;
    	ResultSet rs = null;
    	List <ChefProjet> chefprojets = new ArrayList<ChefProjet>();
    	
    	try{
    		String sql = "select * from chefprojet";
    		 con = DataBase.getConnection();
    		 ps = con.prepareStatement(sql);
    		 rs= ps.executeQuery(); 
    		 while (rs.next())
    		 {
    			 ChefProjet chef = new ChefProjet();
    			 chef.setId(rs.getLong("id"));
    			 chef.setNom(rs.getString("nom"));
    			 chef.setPrenom(rs.getString("prenom"));
    			 chef.setEmail(rs.getString("email"));
    			 chef.setDateInscription(rs.getTimestamp("dateInscription"));
    			 chefprojets.add(chef);
    		 } 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally {
            DataBase.close(con);
        }
  
    	return chefprojets;
    }
    
    public String  delete(String email) {
        ChefProjetDao.deleteChef(email);
        return "adminHomePage";
   }
    
    
    
    public ChefProjet getChefprojet() {
        return chefprojet;
    }
    
    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        chefprojet.setDateInscription( date );
    }
}
