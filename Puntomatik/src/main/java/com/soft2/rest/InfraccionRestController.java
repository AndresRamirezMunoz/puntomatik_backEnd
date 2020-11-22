package com.soft2.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft2.dao.ConductorDaoApi;
import com.soft2.dao.InfraccionDaoApi;
import com.soft2.entity.Conductor;
import com.soft2.entity.Infraccion;

@RestController
@RequestMapping(value = "/infraccion/")
@CrossOrigin
public class InfraccionRestController {

	private final int SMLV = 877;
	private final int[] MULTAS = { 15, 11, 12, 7, 8, 10, 7, 14 };
	private final String[] TIPOMULTA = { "Estacionar en sitios prohibidos", "Exceso de velocidad",
			"Revision tecnomecanica", "Transitar por sitios prohibidos", "No portar licencia", "Soat vencido",
			"Conducir sin licencia", "No detenerse en luz roja" };

	@Autowired
	private InfraccionDaoApi infraccionDaoAPI;
	@Autowired
	private ConductorDaoApi conductorDaoAPI;

	@GetMapping(value = "/all")
	public List<Infraccion> getAll() {
		return infraccionDaoAPI.findAllOrderByFecha();
	}

	@GetMapping(value = "/update/1234")
	public List<Infraccion> updateValue() {
		List<Infraccion> lista = infraccionDaoAPI.findAllOrderByFecha();
		for (int i = 0; i < lista.size(); i++) {
			Infraccion infraccion = lista.get(i);
			infraccion.calcularDatosPrueba(SMLV, MULTAS, TIPOMULTA);
			infraccionDaoAPI.save(infraccion);
		}
		return infraccionDaoAPI.findAllOrderByFecha();
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<Infraccion> getById(@PathVariable("id") long id) {

		Optional<Infraccion> optionalInfraccion = infraccionDaoAPI.findById(id);
		if (optionalInfraccion.isPresent())
			return ResponseEntity.ok(optionalInfraccion.get());
		else
			return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/save/")
	public ResponseEntity<Infraccion> save(@RequestBody Infraccion infraccion) {
		infraccion.calcularDatos(SMLV, MULTAS, TIPOMULTA);
		Optional<Conductor> optionalConductor = conductorDaoAPI.findById(infraccion.getCedulaConductor());
		if (optionalConductor.isPresent()) {
			int puntos = infraccion.getPuntosPerdidos();
			optionalConductor.get().setPuntos(puntos);
			conductorDaoAPI.save(optionalConductor.get());
		}
		Infraccion obj = infraccionDaoAPI.save(infraccion);
		return new ResponseEntity<Infraccion>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Infraccion> delet(@PathVariable Long id) {
		Optional<Infraccion> infraccion = infraccionDaoAPI.findById(id);
		if (infraccion.isPresent()) {
			infraccionDaoAPI.deleteById(id);
		} else {
			return new ResponseEntity<Infraccion>(infraccion.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Infraccion>(infraccion.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/findCC/{cedulaConductor}")
	public List<Infraccion> getByCedulaConductor(@PathVariable("cedulaConductor") Long cedulaConductor) {
		return infraccionDaoAPI.finyByCedulaConductor(cedulaConductor);
	}

	@GetMapping(value = "/findCA/{cedulaAgente}")
	public List<Infraccion> getByCedulaAgente(@PathVariable("cedulaAgente") Long cedulaAgente) {
		return infraccionDaoAPI.finyByCedulaAgente(cedulaAgente);
	}

	@GetMapping(value = "/findByDateRange/{rangoFechaCedula}")
	public List<Infraccion> getByDateRange(@PathVariable("rangoFechaCedula") String rangoFechaCedula) {
		String[] datos = rangoFechaCedula.split("_");
		return infraccionDaoAPI.finyByDateRange(datos[0], datos[1]);
	}

	@GetMapping(value = "/findCount/{rangoFecha}")
	public ResponseEntity<String> getByCount(@PathVariable("rangoFecha") String rangoFechaCedula) {
		String[] datos = rangoFechaCedula.split("_");
		String resut = infraccionDaoAPI.findCount(datos[0], datos[1]);
		return new ResponseEntity<String>(resut, HttpStatus.OK);
	}

	@GetMapping(value = "/findSumPuntos/{rangoFecha}")
	public ResponseEntity<String> getBySumPuntos(@PathVariable("rangoFecha") String rangoFechaCedula) {
		String[] datos = rangoFechaCedula.split("_");
		String resut = infraccionDaoAPI.findSumPuntos(datos[0], datos[1]);
		return new ResponseEntity<String>(resut, HttpStatus.OK);
	}

	@GetMapping(value = "/findSumValor/{rangoFecha}")
	public ResponseEntity<String> getSumValor(@PathVariable("rangoFecha") String rangoFechaCedula) {
		String[] datos = rangoFechaCedula.split("_");
		String resut = infraccionDaoAPI.findSumValor(datos[0], datos[1]);
		return new ResponseEntity<String>(resut, HttpStatus.OK);

	}

	/*
	 
	 */
}
