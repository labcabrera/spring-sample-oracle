package org.lab.samples.oracle.service.participant.jpa;

import java.util.List;
import java.util.Optional;

import org.lab.samples.oracle.model.participant.Participant;
import org.lab.samples.oracle.repositories.ParticipantRepository;
import org.lab.samples.oracle.service.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ParticipantJpaService implements ParticipantService {

	@Autowired
	private ParticipantRepository repository;

	public List<Participant> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Participant> find(Participant beanQuery) {
		Example<Participant> example = Example.of(beanQuery);
		return repository.findAll(example);
	}

	@Override
	public Participant findById(Long id) {
		Optional<Participant> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Participant insert(Participant entity) {
		return repository.save(entity);
	}

	@Override
	public Participant update(Participant entity) {
		return repository.save(entity);
	}

	@Override
	public Participant delete(Long id) {
		Optional<Participant> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return optional.get();
		}
		return null;
	}
}
