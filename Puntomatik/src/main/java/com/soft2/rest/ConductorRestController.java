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
@RequestMapping(value = "/conductor/")
@CrossOrigin
public class ConductorRestController {

	@Autowired
	private ConductorDaoApi conductorDaoAPI;
	@Autowired
	private InfraccionDaoApi infraccionDaoAPI;

	@GetMapping(value = "/all")
	public List<Conductor> getAll() {
		return conductorDaoAPI.findAll();
	}

	@GetMapping(value = "/find/{cedula}")
	public List<Conductor> getByCedulaAgente(@PathVariable("cedula") Long cedula) {
		return conductorDaoAPI.finyByCedula(cedula);
	}
	@GetMapping(value = "/findOne/{id}")
	public ResponseEntity<Conductor> getById(@PathVariable("id") Long id) {
		Optional<Conductor> optionalCondcutor =conductorDaoAPI.findById(id);
		if (optionalCondcutor.isPresent()) {
			int puntos=0;
			List<Infraccion> infracciones=infraccionDaoAPI.finyByCedulaConductor(optionalCondcutor.get().getCedula());
			for (int i = 0; i < infracciones.size(); i++) {
				puntos-=infracciones.get(i).getPuntosPerdidos();	
			}optionalCondcutor.get().setPuntos(puntos);
			return ResponseEntity.ok(optionalCondcutor.get());
		}else
			return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Conductor> save(@RequestBody Conductor conductor) {
		Conductor obj = conductorDaoAPI.save(conductor);
		return new ResponseEntity<Conductor>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{cedula}")
	public ResponseEntity<Conductor> delet(@PathVariable Long cedula) {
		Optional<Conductor> conductor = conductorDaoAPI.findById(cedula);
		if (conductor.isPresent()) {
			conductorDaoAPI.deleteById(cedula);
		} else {
			return new ResponseEntity<Conductor>(conductor.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Conductor>(conductor.get(), HttpStatus.OK);
	}

}
