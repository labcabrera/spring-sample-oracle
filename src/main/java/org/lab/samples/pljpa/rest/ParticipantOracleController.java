package org.lab.samples.pljpa.rest;

import org.lab.samples.pljpa.service.participant.oracle.ParticipantOracleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/oracle")
public class ParticipantOracleController extends AbstractParticipantController {

	public ParticipantOracleController(ParticipantOracleService service) {
		super(service);
	}

}
