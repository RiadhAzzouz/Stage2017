package com.stage.beans;

import javax.persistence.Column;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Projet {

	@Column(name ="nom")
	@NotNull( message = "Veuillez saisir un nom " )
	private String nom;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name="id_chef")
	private long id_chef;
	
	@Column(name="pourcentage")
	private int pourcentage;
	
	@Column(name ="dateAjout")
	private Timestamp dateAjout ;
	
	@Column(name="dateAjout")
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

	public long getId_chef() {
		return id_chef;
	}

	public void setId_chef(long id_chef) {
		this.id_chef = id_chef;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	
	
	
}
