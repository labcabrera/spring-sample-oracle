package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantDeleteProcedure;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantInsertProcedure;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantReadProcedure;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantUpdateProcedure;

@Repository
public class ParticipantOracleProcedureService {

	private final String username;
	private final ParticipantReadProcedure readFunction;
	private final ParticipantInsertProcedure insertProcedure;
	private final ParticipantUpdateProcedure updateProcedure;
	private final ParticipantDeleteProcedure deleteProcedure;

	public ParticipantOracleProcedureService(DataSource dataSource, StructDefinitionService definitionService,
		Environment env) {
		readFunction = new ParticipantReadProcedure(dataSource, definitionService);
		insertProcedure = new ParticipantInsertProcedure(dataSource, definitionService);
		updateProcedure = new ParticipantUpdateProcedure(dataSource, definitionService);
		deleteProcedure = new ParticipantDeleteProcedure(dataSource, definitionService);
		username = env.getProperty("app.env.audit.username", "LBARRA1");
	}

	public List<Participant> find(Participant beanQuery) {
		return readFunction.find(beanQuery);
	}

	public Participant findById(Long id) {
		Participant queryBean = new Participant();
		queryBean.setPar_prc_prc_val(id);
		List<Participant> list = find(queryBean);
		return list.isEmpty() ? null : list.iterator().next();
	}

	public Participant insert(Participant entity) {
		return insertProcedure.insert(entity, username);
	}

	public Participant update(Participant entity) {
		return updateProcedure.update(entity, username);
	}

	public Participant delete(Long id) {
		Participant entity = new Participant();
		entity.setPar_prc_prc_val(id);
		return deleteProcedure.delete(entity);
	}
}
