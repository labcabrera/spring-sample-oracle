package com.mapfre.dgtp.samples.pljpa.service;

import java.util.List;

import com.mapfre.dgtp.samples.pljpa.model.Participant;

public interface ParticipantService {

	List<Participant> findAll();

	List<Participant> find(Participant beanQuery);

	Participant findById(Long id);

	Participant insert(Participant entity);

	Participant update(Participant entity);

	Participant delete(Long id);

}
