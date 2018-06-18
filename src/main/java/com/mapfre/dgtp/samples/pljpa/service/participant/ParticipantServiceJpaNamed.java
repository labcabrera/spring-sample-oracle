package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

@Service
public class ParticipantServiceJpaNamed implements ParticipantService {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Participant> findAll() {

		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(Participant.NamedQueries.FIND);
		Participant objectQuery = new Participant();

		query.setParameter(1, objectQuery);
		List results = query.getResultList();

		System.out.println(results);
		return null;
	}

}
