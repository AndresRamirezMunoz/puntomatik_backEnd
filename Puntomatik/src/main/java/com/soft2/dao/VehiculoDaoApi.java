package com.soft2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soft2.entity.Vehiculo;

public interface VehiculoDaoApi extends JpaRepository<Vehiculo, Long> {

	@Query(value = "SELECT * FROM vehiculo WHERE propiestario=?1 ", nativeQuery = true)
	List<Vehiculo> finyByCedulaConductor(Long cedulaConductor);

}
