package com.GestionTicket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionTicket.Entitie.Compte;
import com.GestionTicket.Repository.CompteRepository;

@Service
public class CompteService {

	@Autowired
	private CompteRepository compteRepository;
	
	public void saveCompte(Compte compte) {
        compteRepository.save(compte);
    }
}
