package org.lab.samples.pljpa.service.participant;

import java.util.List;

import org.lab.samples.pljpa.model.Participant;

public interface ParticipantService {

	List<Participant> findAll();

	List<Participant> find(Participant beanQuery);

	Participant findById(Long id);

	Participant insert(Participant entity);

	Participant update(Participant entity);

	Participant delete(Long id);

}
