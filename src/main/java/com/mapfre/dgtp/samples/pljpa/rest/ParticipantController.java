package com.mapfre.dgtp.samples.pljpa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJdbc;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJdbcBasic;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJpa;
import com.mapfre.dgtp.samples.pljpa.service.participant.ParticipantServiceJpaNamed;

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
	private ParticipantServiceJpaNamed serviceJpaNamed;

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

	@GetMapping("/sp")
	public List<Participant> findStoredProcedure() {
		return serviceJpaNamed.findAll();
	}

}
