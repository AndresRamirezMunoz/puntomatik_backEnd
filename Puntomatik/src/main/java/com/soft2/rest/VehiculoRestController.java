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

import com.soft2.dao.VehiculoDaoApi;
import com.soft2.entity.Vehiculo;

@RestController
@RequestMapping(value = "/vehiculo/")
@CrossOrigin
public class VehiculoRestController {

	@Autowired
	private VehiculoDaoApi vehiculoDaoAPI;

	@GetMapping(value = "/all")
	public List<Vehiculo> getAll() {
		return vehiculoDaoAPI.findAll();
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<Vehiculo> getById(@PathVariable("id") Long id) {
		Optional<Vehiculo> optionalVehiculo = vehiculoDaoAPI.findById(id);
		if (optionalVehiculo.isPresent())
			return ResponseEntity.ok(optionalVehiculo.get());
		else
			return ResponseEntity.noContent().build();

	}

	@PostMapping(value = "/save")
	public ResponseEntity<Vehiculo> save(@RequestBody Vehiculo vehiculo) {
		Vehiculo obj = vehiculoDaoAPI.save(vehiculo);
		return new ResponseEntity<Vehiculo>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/delete/{cedula}")
	public ResponseEntity<Vehiculo> delet(@PathVariable Long cedula) {
		Optional<Vehiculo> vehiculo = vehiculoDaoAPI.findById(cedula);
		if (vehiculo.isPresent()) {
			vehiculoDaoAPI.deleteById(cedula);
		} else {
			return new ResponseEntity<Vehiculo>(vehiculo.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Vehiculo>(vehiculo.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findCC/{cedulaConductor}")
	public List<Vehiculo> getByCedulaConductor(@PathVariable("cedulaConductor") Long cedulaConductor) {
		return vehiculoDaoAPI.finyByCedulaConductor(cedulaConductor);
	}

}
