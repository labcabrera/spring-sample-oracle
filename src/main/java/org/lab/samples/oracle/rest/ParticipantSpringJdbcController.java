package org.lab.samples.oracle.rest;

import org.lab.samples.oracle.service.participant.spring.ParticipantSpringJdbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/spring-jdbc")
public class ParticipantSpringJdbcController extends AbstractParticipantController {

	public ParticipantSpringJdbcController(ParticipantSpringJdbcService service) {
		super(service);
	}

}
