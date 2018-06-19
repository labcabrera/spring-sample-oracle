package com.mapfre.dgtp.samples.pljpa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantOracleProcedureService;

@RestController
@RequestMapping("/api/participants/oracle")
public class ParticipantOracleController {

	@Autowired
	private ParticipantOracleProcedureService service;

	@GetMapping("/{id}")
	public ResponseEntity<Participant> findById(@PathVariable Long id) {
		Participant participant = service.findById(id);
		if (participant == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(participant);
	}

	@PostMapping
	public List<Participant> find(@RequestBody Participant beanQuery) {
		return service.find(beanQuery);
	}

	@PutMapping
	public Participant insert(@RequestBody Participant entity) {
		return service.insert(entity);
	}

	@PatchMapping
	public Participant update(@RequestBody Participant entity) {
		return service.update(entity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Participant> delete(@PathVariable Long id) {
		Participant participant = service.delete(id);
		if (participant == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(participant);
	}

}
