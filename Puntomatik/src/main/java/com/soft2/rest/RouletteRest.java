package com.soft2.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft2.dao.RouletteDao;
import com.soft2.entity.Roulette;

@RestController
@RequestMapping("roulette")
public class RouletteRest {

	@Autowired
	private RouletteDao rouletteDao;

	@PostMapping
	public ResponseEntity<Roulette> createRoulette(@RequestBody Roulette roulette) {
		Roulette newRoulette = rouletteDao.save(roulette);
		return ResponseEntity.ok(newRoulette);
	}

	@GetMapping(value = "open/{rouletteId}")
	public ResponseEntity<Roulette> openRouletteById(@PathVariable("rouletteId") int rouletteId) {
		ResponseEntity<Roulette> response = getRouletteById(rouletteId);
		if (response.getStatusCodeValue() == 200) {
			Roulette updateRoulette = response.getBody();
			updateRoulette.openRoulette();
			rouletteDao.save(updateRoulette);
			return new ResponseEntity<Roulette>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Roulette>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "close/{rouletteId}")
	public ResponseEntity<Integer> closeRouletteById(@PathVariable("rouletteId") int rouletteId) {
		ResponseEntity<Roulette> response = getRouletteById(rouletteId);
		if (response.getStatusCodeValue() == 200) {
			Roulette updateRoulette = response.getBody();
			updateRoulette.closeRoulette();
			rouletteDao.save(updateRoulette);
			int value=rouletteDao.getValueByDateQuery(updateRoulette.getId(), updateRoulette.getStartTime(),
					updateRoulette.getEndTime());
			return ResponseEntity.ok(value);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Roulette>> getRoulettes() {
		List<Roulette> roulettes = rouletteDao.findAll();
		return ResponseEntity.ok(roulettes);
	}

	@RequestMapping(value = "/{rouletteId}")
	public ResponseEntity<Roulette> getRouletteById(@PathVariable("rouletteId") int rouletteId) {
		Optional<Roulette> optionalRoulette = rouletteDao.findById(rouletteId);

		if (optionalRoulette.isPresent())
			return ResponseEntity.ok(optionalRoulette.get());
		else
			return ResponseEntity.noContent().build();
	}

}
