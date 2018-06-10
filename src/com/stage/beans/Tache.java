package com.stage.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Tache {
	
	@Column(name ="nom")
	@NotNull( message = "Veuillez saisir un nom " )
	private String nom;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name="id_projet")
	private long id_projet;
	
	@Column(name="id_personnel")
	private long id_personnel;
	
	
	@Column(name ="dateAjout")
	private Timestamp dateAjout ;
	
	@Column(name="pourcentage")
	private int pourcentage;
	
	
	
	public long getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(long id_personnel) {
		this.id_personnel = id_personnel;
	}

	public Timestamp getDateAjout(){
		return dateAjout;
	}
	
	public void setDateAjout(Timestamp dateAjout){
		this.dateAjout = dateAjout;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_projet() {
		return id_projet;
	}

	public void setId_projet(long id_projet) {
		this.id_projet = id_projet;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	

}
