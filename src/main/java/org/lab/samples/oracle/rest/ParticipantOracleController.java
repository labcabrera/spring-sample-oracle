package org.lab.samples.oracle.rest;

import org.lab.samples.oracle.service.participant.oracle.ParticipantOracleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/oracle")
public class ParticipantOracleController extends AbstractParticipantController {

	public ParticipantOracleController(ParticipantOracleService service) {
		super(service);
	}

}
