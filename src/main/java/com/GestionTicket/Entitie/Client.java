package com.GestionTicket.Entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Idclient")
    private Long Idclient;
    
   
    @Column(name = "nom")
    private String nom;

  
    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

   
    @Column(name = "email")
    private String email;
    
     @OneToOne
	 @JoinColumn(name = "CompteId", referencedColumnName = "compteId")
	
    private Compte compte;

	public Long getIdclient() {
		return Idclient;
	}

	public void setIdclient(Long idclient) {
		Idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
