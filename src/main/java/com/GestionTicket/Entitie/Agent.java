package com.GestionTicket.Entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;



@Entity
public class Agent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Idagent")
    private Long Idagent;

	
    @Column(name = "nom")
    private String nom;
	
    @Column(name = "email")
    private String email;
	
	@OneToOne
	@JoinColumn(name = "CompteId", referencedColumnName = "compteId")
    private Compte compte;

	public Long getIdagent() {
		return Idagent;
	}

	public void setIdagent(Long idagent) {
		Idagent = idagent;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
