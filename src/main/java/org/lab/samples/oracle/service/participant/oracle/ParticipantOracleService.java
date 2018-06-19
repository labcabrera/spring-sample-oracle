package org.lab.samples.oracle.service.participant.oracle;

import java.util.List;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.participant.Participant;
import org.lab.samples.oracle.service.participant.ParticipantService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantOracleService implements ParticipantService {

	private final String username;
	private final ParticipantOracleReadProcedure readFunction;
	private final ParticipantOracleInsertProcedure insertProcedure;
	private final ParticipantOracleUpdateProcedure updateProcedure;
	private final ParticipantOracleDeleteProcedure deleteProcedure;

	public ParticipantOracleService(DataSource dataSource, StructDefinitionService definitionService, Environment env) {
		readFunction = new ParticipantOracleReadProcedure(dataSource, definitionService);
		insertProcedure = new ParticipantOracleInsertProcedure(dataSource, definitionService);
		updateProcedure = new ParticipantOracleUpdateProcedure(dataSource, definitionService);
		deleteProcedure = new ParticipantOracleDeleteProcedure(dataSource, definitionService);
		username = env.getProperty("app.env.audit.username");
	}

	@Override
	public List<Participant> findAll() {
		return readFunction.find(new Participant());
	}

	@Override
	public List<Participant> find(Participant beanQuery) {
		return readFunction.find(beanQuery);
	}

	@Override
	public Participant findById(Long id) {
		Participant queryBean = new Participant();
		queryBean.setPar_prc_prc_val(id);
		List<Participant> list = find(queryBean);
		return list.isEmpty() ? null : list.iterator().next();
	}

	@Override
	public Participant insert(Participant entity) {
		return insertProcedure.insert(entity, username);
	}

	@Override
	public Participant update(Participant entity) {
		return updateProcedure.update(entity, username);
	}

	@Override
	public Participant delete(Long id) {
		Participant entity = new Participant();
		entity.setPar_prc_prc_val(id);
		return deleteProcedure.delete(entity);
	}

}
