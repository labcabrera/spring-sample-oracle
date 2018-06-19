package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantOracleService;

@RestController
@RequestMapping("/api/participants/oracle")
public class ParticipantOracleController extends AbstractParticipantController {

	public ParticipantOracleController(ParticipantOracleService service) {
		super(service);
	}

}
