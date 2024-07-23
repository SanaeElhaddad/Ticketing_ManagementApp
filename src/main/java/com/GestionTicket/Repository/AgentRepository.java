package com.GestionTicket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionTicket.Entitie.Agent;

public interface AgentRepository extends JpaRepository<Agent,Long> {

}
