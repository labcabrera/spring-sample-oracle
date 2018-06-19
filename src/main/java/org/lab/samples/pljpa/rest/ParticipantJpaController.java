package org.lab.samples.pljpa.rest;

import org.lab.samples.pljpa.service.participant.jpa.ParticipantJpaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants/jpa")
public class ParticipantJpaController extends AbstractParticipantController {

	public ParticipantJpaController(ParticipantJpaService service) {
		super(service);
	}

}
