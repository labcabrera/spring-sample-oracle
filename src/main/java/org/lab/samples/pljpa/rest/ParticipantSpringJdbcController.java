package org.lab.samples.pljpa.rest;

import org.lab.samples.pljpa.service.participant.spring.ParticipantSpringJdbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/spring-jdbc")
public class ParticipantSpringJdbcController extends AbstractParticipantController {

	public ParticipantSpringJdbcController(ParticipantSpringJdbcService service) {
		super(service);
	}

}
