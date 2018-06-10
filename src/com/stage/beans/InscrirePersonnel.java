package com.stage.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;


import com.stage.dao.DataBase;
import com.stage.dao.PersonnelDao;

@ManagedBean(name="InscrirePersonnel")
@ApplicationScoped
public class InscrirePersonnel implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Personnel personnel;
	

	
	@EJB
    private PersonnelDao    personnelDao;
	
    
    public InscrirePersonnel() {
        personnel = new Personnel();
    }

    public String inscrire() {
    	initialiserDateInscription();
    	personnel.setAccepted(false);
        personnelDao.creer( personnel );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "loginPersonnel";
    }
    
    public List<Personnel> getPersonnels() {
    	PreparedStatement ps = null;
    	Connection con = null;
    	ResultSet rs = null;
    	List <Personnel> personnels = new ArrayList<Personnel>();
    	
    	try{
    		String sql = "select * from personnel";
    		 con = DataBase.getConnection();
    		 ps = con.prepareStatement(sql);
    		 rs= ps.executeQuery(); 
    		 while (rs.next())
    		 {
    			 Personnel pers = new Personnel();
    			 pers.setId(rs.getLong("id"));
    			 pers.setNom(rs.getString("nom"));
    			 pers.setPrenom(rs.getString("prenom"));
    			 pers.setEmail(rs.getString("email"));
    			 pers.setDateInscription(rs.getTimestamp("dateInscription"));
    			 personnels.add(pers);
    		 } 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally {
            DataBase.close(con);
        }
  
    	return personnels;
    }
    
    public String  delete(String email) {
        PersonnelDao.deletePersonnel(email);
        return "adminHomePage";
   }
    
    
    
   

    public Personnel getPersonnel() {
        return personnel;
    }
    
    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        personnel.setDateInscription( date );
    }

}
