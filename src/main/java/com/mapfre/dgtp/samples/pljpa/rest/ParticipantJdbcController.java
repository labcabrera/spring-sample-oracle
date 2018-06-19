package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.service.participant.jdbc.ParticipantJdbcService;

@RestController
@RequestMapping("/api/participants/jdbc")
public class ParticipantJdbcController extends AbstractParticipantController {

	public ParticipantJdbcController(ParticipantJdbcService service) {
		super(service);
	}

}
