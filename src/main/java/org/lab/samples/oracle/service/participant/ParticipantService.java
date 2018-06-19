package org.lab.samples.oracle.service.participant;

import java.util.List;

import org.lab.samples.oracle.model.participant.Participant;

/**
 * Basic participant CRUD service.
 */
public interface ParticipantService {

	List<Participant> findAll();

	List<Participant> find(Participant example);

	Participant findById(Long id);

	Participant insert(Participant entity);

	Participant update(Participant entity);

	Participant delete(Long id);

}
