package com.soft2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.soft2.entity.Conductor;

public interface ConductorDaoApi extends JpaRepository<Conductor, Long> {

	@Query(value = "SELECT * FROM conductor WHERE cedula=?1", nativeQuery = true)
	List<Conductor> finyByCedula(Long cedula);
}






