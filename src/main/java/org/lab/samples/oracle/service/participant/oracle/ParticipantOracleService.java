package org.lab.samples.oracle.service.participant.oracle;

import java.util.List;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.lab.samples.oracle.model.participant.Participant;
import org.lab.samples.oracle.service.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantOracleService implements ParticipantService {

	private final String username;
	private final ParticipantOracleReadProcedure readFunction;
	private final ParticipantOracleInsertProcedure insertProcedure;
	private final ParticipantOracleUpdateProcedure updateProcedure;
	private final ParticipantOracleDeleteProcedure deleteProcedure;

	public ParticipantOracleService( //@formatter:off
		DataSource dataSource,
		@Qualifier("mapper-annotation") StructMapperService mapperService,
		Environment env) { //@formatter:on

		username = env.getProperty("app.env.audit.username");
		readFunction = new ParticipantOracleReadProcedure(dataSource, mapperService);
		insertProcedure = new ParticipantOracleInsertProcedure(dataSource, mapperService);
		updateProcedure = new ParticipantOracleUpdateProcedure(dataSource, mapperService);
		deleteProcedure = new ParticipantOracleDeleteProcedure(dataSource, mapperService);
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
		queryBean.setId(id);
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
		entity.setId(id);
		return deleteProcedure.delete(entity);
	}

}
