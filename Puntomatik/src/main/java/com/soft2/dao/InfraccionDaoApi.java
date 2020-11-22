package com.soft2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soft2.entity.Infraccion;

public interface InfraccionDaoApi extends JpaRepository<Infraccion, Long> {

	@Query(value = "SELECT * FROM infraccion WHERE cedula_conductor=?1 ORDER BY fecha DESC", nativeQuery = true)
	List<Infraccion> finyByCedulaConductor(Long cedulaConductor);

	@Query(value = "SELECT * FROM infraccion WHERE cedula_agente=?1 ORDER BY fecha DESC", nativeQuery = true)
	List<Infraccion> finyByCedulaAgente(Long cedulaAgente);

	@Query(value = "SELECT * FROM infraccion ORDER BY fecha DESC", nativeQuery = true)
	List<Infraccion> findAllOrderByFecha();

	@Query(value = "SELECT * FROM infraccion WHERE fecha BETWEEN date(?1) AND date(?2) ORDER BY fecha DESC", nativeQuery = true)
	List<Infraccion> finyByDateRange(String dateStart, String dateEnd);
	//select sum(puntos_perdidos) from infraccion WHERE cedula_conductor=186828533; SELECT SUM(PUNTOS_PERDIDOS) FROM INFRACCION
	
	@Query(value = "SELECT count(ID) FROM infraccion WHERE fecha BETWEEN date(?1) AND date(?2)", nativeQuery = true)
	String findCount(String dateStart, String dateEnd);
	//SELECT sum(valor) FROM INFRACCION WHERE FECHA BETWEEN '2020-06-15' AND '2020-10-15';
	
	@Query(value = "SELECT sum(puntos_perdidos) FROM infraccion WHERE fecha BETWEEN date(?1) AND date(?2)", nativeQuery = true)
	String findSumPuntos(String dateStart, String dateEnd);
	
	@Query(value = "SELECT sum(paga) FROM infraccion WHERE fecha BETWEEN date(?1) AND date(?2)", nativeQuery = true)
	String findSumValor(String dateStart, String dateEnd);
	
	
//	@Query(value = "select sum(puntos_perdidos) from infraccion WHERE cedula_agente=?1 ", nativeQuery = true)
//	int getTotalPuntoByCedula(Long cedulaAgente);

}