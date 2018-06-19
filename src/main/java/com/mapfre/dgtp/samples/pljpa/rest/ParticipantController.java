package com.mapfre.dgtp.samples.pljpa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantOracleProcedureService;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJdbc;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJdbcBasic;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJpa;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

	@Autowired
	private ParticipantServiceJdbc serviceJdbc;

	@Autowired
	private ParticipantServiceJdbcBasic serviceJdbcBasic;

	@Autowired
	private ParticipantServiceJpa serviceJpa;

	@Autowired
	private ParticipantOracleProcedureService participantOracleProcedureService;

	@GetMapping("/oracle-function/{id}")
	public ResponseEntity<Participant> findSpringOracleById(@PathVariable Long id) {
		Participant participant = participantOracleProcedureService.findById(id);
		if (participant == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(participant);
	}

	@PostMapping("/oracle-function")
	public List<Participant> findSpringOracle(@RequestBody Participant beanQuery) {
		return participantOracleProcedureService.find(beanQuery);
	}

	@GetMapping("/jdbc")
	public List<Participant> findJdbc() {
		return serviceJdbc.findAll();
	}

	@GetMapping("/jdbc-basic")
	public List<Participant> findJdbcBasic() {
		return serviceJdbcBasic.findAll();
	}

	@GetMapping("/jpa")
	public List<Participant> findJpa() {
		return serviceJpa.findAll();
	}

}
