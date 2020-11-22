package com.soft2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soft2.entity.Agente;

public interface AgenteDaoApi extends JpaRepository<Agente, Long> {

	@Query(value = "SELECT * FROM agente WHERE cedula=?1", nativeQuery = true)
	List<Agente> finyByCedula(Long cedula);


}