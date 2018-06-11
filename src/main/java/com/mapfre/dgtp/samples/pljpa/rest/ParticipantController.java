package com.mapfre.dgtp.samples.pljpa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.impl.ParticipantServiceJpa;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

	@Autowired
	private ParticipantServiceJpa serviceJpa;

	@GetMapping
	public List<Participant> findJpa() {
		return serviceJpa.findAll();
	}

}
