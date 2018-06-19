package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.service.participant.spring.ParticipantSpringJdbcService;

@RestController
@RequestMapping("/api/participants/spring-jdbc")
public class ParticipantSpringJdbcController extends AbstractParticipantController {

	public ParticipantSpringJdbcController(ParticipantSpringJdbcService service) {
		super(service);
	}

}
