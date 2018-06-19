package org.lab.samples.oracle.rest.participant;

import org.lab.samples.oracle.service.participant.jdbc.ParticipantJdbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/participants/jdbc")
@Api("Participant JDBC (not implemented yet)")
public class ParticipantJdbcController extends AbstractParticipantController {

	public ParticipantJdbcController(ParticipantJdbcService service) {
		super(service);
	}

}
