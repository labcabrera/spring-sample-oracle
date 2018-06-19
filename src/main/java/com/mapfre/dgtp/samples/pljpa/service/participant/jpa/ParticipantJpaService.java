package com.mapfre.dgtp.samples.pljpa.service.participant.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.repositories.ParticipantRepository;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

@Service
public class ParticipantJpaService implements ParticipantService {

	@Autowired
	private ParticipantRepository repository;

	public List<Participant> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Participant> find(Participant beanQuery) {
		// TODO filter fields
		return repository.findAll();
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
