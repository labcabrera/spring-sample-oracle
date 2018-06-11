package com.mapfre.dgtp.samples.pljpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.repositories.ParticipantRepository;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

@Service
public class ParticipantServiceJpa implements ParticipantService {

	@Autowired
	private ParticipantRepository repository;

	public List<Participant> findAll() {
		return repository.findAll();
	}
}
