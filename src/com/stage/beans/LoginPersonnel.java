package com.stage.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.stage.dao.LoginPersonnelDao;

@ManagedBean(name = "loginPersonnel")
@SessionScoped
public class LoginPersonnel implements Serializable {

	private static final long serialVersionUID = 1L;
    private String mot_de_passe;
    private String message, nom;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getMot_de_passe() {
        return mot_de_passe;
    }
 
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
 
    public String getNom() {
        return nom;
    }
 
    public void setNom(String nom) {
        this.nom = nom;
    }
 
    public String loginProject() {
        boolean result = LoginPersonnelDao.login(nom, mot_de_passe);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("nom", nom);
 
            return "personnelHomePage";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "loginPersonnel";
        }
    }
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      return "loginPersonnel";
   }

	
}
