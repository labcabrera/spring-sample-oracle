package org.lab.samples.pljpa.rest;

import org.lab.samples.pljpa.service.participant.jdbc.ParticipantJdbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/jdbc")
public class ParticipantJdbcController extends AbstractParticipantController {

	public ParticipantJdbcController(ParticipantJdbcService service) {
		super(service);
	}

}
