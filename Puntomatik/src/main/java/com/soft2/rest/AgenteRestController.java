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

import com.soft2.dao.AgenteDaoApi;
import com.soft2.entity.Agente;

@RestController
@RequestMapping(value = "/agente/")
@CrossOrigin
public class AgenteRestController {

	@Autowired
	private AgenteDaoApi agenteDaoAPI;

	@GetMapping(value = "/all")
	public List<Agente> getAll() {
		return agenteDaoAPI.findAll();
	}

	@GetMapping(value = "/find/{cedula}")
	public List<Agente> getByCedulaAgente(@PathVariable("cedula") Long cedula) {
		return agenteDaoAPI.finyByCedula(cedula);
	}
	@GetMapping(value = "/findOne/{id}")
	public ResponseEntity<Agente> getById(@PathVariable("id") Long id) {
		Optional<Agente> optionalAgente = agenteDaoAPI.findById(id);
		if (optionalAgente.isPresent())
			return ResponseEntity.ok(optionalAgente.get());
		else
			return ResponseEntity.noContent().build();

	}

	@PostMapping(value = "/save")
	public ResponseEntity<Agente> save(@RequestBody Agente agente) {
		Agente obj = agenteDaoAPI.save(agente);
		return new ResponseEntity<Agente>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{cedula}")
	public ResponseEntity<Agente> delete(@PathVariable Long cedula) {

		Optional<Agente> agente = agenteDaoAPI.findById(cedula);
		if (agente.isPresent()) {
			agenteDaoAPI.deleteById(cedula);
			return new ResponseEntity<Agente>(agente.get(), HttpStatus.OK);

		} else {
			return new ResponseEntity<Agente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
