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
import com.stage.dao.TacheDao;

@ManagedBean (name="AjouterTache")
@ApplicationScoped
public class AjouterTache implements Serializable {

	private static final long serialVersionUID = 1L;
	private Tache tache;
	
	@EJB
	private TacheDao tacheDao;
	
	public AjouterTache() {
		tache = new Tache();
    }
	
	 public Tache getTache() {
	    	return tache;
	 }
	 
	 public void inscrire() {
	        initialiserDateInscription();
	        tacheDao.creer( tache );
	        FacesMessage message = new FacesMessage( "Succès de l'ajout !" );
	        FacesContext.getCurrentInstance().addMessage( null, message );
	        
	 }
	
	 private void initialiserDateInscription() {
	        Timestamp date = new Timestamp( System.currentTimeMillis() );
	        tache.setDateAjout( date );
	 }
	 
	 public List<Tache> getTaches() {
	    	PreparedStatement ps = null;
	    	Connection con = null;
	    	ResultSet rs = null;
	    	List <Tache> taches = new ArrayList<Tache>();
	    	
	    	try{
	    		String sql = "select * from tache";
	    		 con = DataBase.getConnection();
	    		 ps = con.prepareStatement(sql);
	    		 rs= ps.executeQuery(); 
	    		 while (rs.next())
	    		 {
	    			 Tache tache = new Tache();
	    			 tache.setId(rs.getLong("id"));
	    			 tache.setNom(rs.getString("nom"));
	    			 tache.setId_projet(rs.getLong("id_projet"));
	    			 tache.setId_personnel(rs.getLong("id_personnel"));
	    			 tache.setPourcentage(rs.getInt("pourcentage"));
	    			 tache.setDateAjout(rs.getTimestamp("dateAjout"));
	    			 taches.add(tache);
	    		 } 
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally {
	            DataBase.close(con);
	        }
	  
	    	return taches;
	 }
	 
	 public String  delete( String nom ) {
         TacheDao.deleteTache(nom);
         return "chefHomePage";
     }
	 
	 public String editTache(long id, int pourcentage) {
	        PreparedStatement ps = null;
	        Connection con = null;
	        if (id != 0) {
	            try {
	            	con = DataBase.getConnection();
	    			
	                String sql =  "UPDATE tache set  pourcentage = ? WHERE id= ?" ;
	                ps = con.prepareStatement(sql);
	                ps.setInt(1, pourcentage);
	                ps.setLong(2, id);
	                int i = ps.executeUpdate();
	                if (i > 0) {
	                    System.out.println("Row updated successfully");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	            	 DataBase.close(con);
	            }
	        }
	        return "personnelHomePage";
	    }
	 
}
