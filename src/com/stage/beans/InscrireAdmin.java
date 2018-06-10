package com.stage.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.stage.dao.DataBase;
import java.sql.*;

import com.stage.dao.AdminDao;

@ManagedBean (name="InscrireAdmin")
@ApplicationScoped
public class InscrireAdmin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Administrateur       administrateur;
	
	

    @EJB
    private AdminDao    adminDao;
    
    public InscrireAdmin() {
    	administrateur = new Administrateur();
    }
    
    public Administrateur getAdministrateur() {
    	return administrateur;
    }
    
    public String inscrire() {
        initialiserDateInscription();
        adminDao.creer( administrateur );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "loginAdmin";
    }
    

    public List<Administrateur> getAdministrateurs() {
    	PreparedStatement ps = null;
    	Connection con = null;
    	ResultSet rs = null;
    	List <Administrateur> administrateurs = new ArrayList<Administrateur>();
    	
    	try{
    		String sql = "select * from administrateur";
    		 con = DataBase.getConnection();
    		 ps = con.prepareStatement(sql);
    		 rs= ps.executeQuery(); 
    		 while (rs.next())
    		 {
    			 Administrateur admin = new Administrateur();
    			 admin.setId(rs.getLong("id"));
    			 admin.setNom(rs.getString("nom"));
    			 admin.setPrenom(rs.getString("prenom"));
    			 admin.setEmail(rs.getString("email"));
    			 admin.setDateInscription(rs.getTimestamp("dateInscription"));
    			 administrateurs.add(admin);
    		 } 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally {
            DataBase.close(con);
        }
  
    	return administrateurs;
    }
    
    public String  delete(String email) {
         AdminDao.deleteAdmin(email);
         return "adminHomePage";
    }
    
    
    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        administrateur.setDateInscription( date );
    }

}
