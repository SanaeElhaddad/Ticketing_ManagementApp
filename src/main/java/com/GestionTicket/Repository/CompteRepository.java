package com.GestionTicket.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionTicket.Entitie.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	Optional<Compte> findByEmail(String email);

}
