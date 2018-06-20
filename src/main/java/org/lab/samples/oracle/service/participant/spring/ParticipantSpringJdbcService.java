package org.lab.samples.oracle.service.participant.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.NotImplementedException;
import org.lab.samples.oracle.model.participant.Participant;
import org.lab.samples.oracle.service.participant.ParticipantService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ParticipantSpringJdbcService implements ParticipantService {

	protected final SimpleJdbcCall jdbcCall;

	public ParticipantSpringJdbcService(DataSource dataSource) {
		log.debug("Configuring SimpleJdbcCall");
		//@formatter:off
		jdbcCall = new SimpleJdbcCall(dataSource)
			.withFunctionName("dl_gnl_par.f_get")
			.withCatalogName("MPD_LD")
			//.useInParameterNames("p_o_amd_gnl_par_s")
			//.withoutProcedureColumnMetaDataAccess()
			.returningResultSet("O_AMD_GNL_PAR_ST", new RowMapper() {

				@Override
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					throw new NotImplementedException("Not implemented");
				}
				
			});
	}

	@SuppressWarnings("unused")
	@Override
	public List<Participant> findAll() {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<Participant> find(Participant beanQuery) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Participant findById(Long id) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Participant insert(Participant entity) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Participant update(Participant entity) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Participant delete(Long id) {
		throw new NotImplementedException("Not implemented");
	}

}
