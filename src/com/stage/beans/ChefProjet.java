package com.stage.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
public class ChefProjet {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "nom" )
	@NotNull( message = "Veuillez saisir un nom " )
	@Size( min = 4, message = "Le nom  doit contenir au moins 4 caractères" )
	private String nom;
	
	
	@Column( name = "prenom" )
	@NotNull( message = "Veuillez saisir un prenom" )
	@Size( min = 4, message = "Le prenom  doit contenir au moins 4 caractères" )
	private String prenom;
	
	@NotNull( message = "Veuillez saisir une adresse email" )
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" )
	private String email;
	
	@Column( name = "mot_de_passe" )
	@NotNull( message = "Veuillez saisir un mot de passe" )
	@Size( min = 3, message = "Le mot de passe doit contenir au moins 8 caractères" )
	private String mot_de_passe;
	boolean accepted;
	
	@Column( name = "dateInscription" )
    private Timestamp dateInscription;

	public Timestamp getDateInscription(){
		return dateInscription;
	}
	
	public void setDateInscription(Timestamp dateInscription){
		this.dateInscription = dateInscription;
	}
	
	public boolean getAccepted() {
		return accepted;
	}
	
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
}
