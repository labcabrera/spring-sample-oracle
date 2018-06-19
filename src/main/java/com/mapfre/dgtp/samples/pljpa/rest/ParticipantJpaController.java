package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.service.participant.jpa.ParticipantJpaService;

@RestController
@RequestMapping("/api/participants/jpa")
public class ParticipantJpaController extends AbstractParticipantController {

	public ParticipantJpaController(ParticipantJpaService service) {
		super(service);
	}

}
