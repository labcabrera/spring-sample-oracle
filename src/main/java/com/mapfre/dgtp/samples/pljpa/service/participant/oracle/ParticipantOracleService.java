package com.mapfre.dgtp.samples.pljpa.service.participant.oracle;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

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
		username = env.getProperty("app.env.audit.username", "LBARRA1");
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
