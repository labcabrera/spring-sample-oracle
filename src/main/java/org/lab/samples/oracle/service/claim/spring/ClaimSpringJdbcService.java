package org.lab.samples.oracle.service.claim.spring;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.NotImplementedException;
import org.lab.samples.oracle.model.participant.claim.OSinAccInS;
import org.lab.samples.oracle.model.participant.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleTypes;

@Service
@Slf4j
public class ClaimSpringJdbcService implements ClaimService {

	private final SimpleJdbcCall jdbcCall;

	public ClaimSpringJdbcService(DataSource dataSource) {
		//@formatter:off
		jdbcCall = new SimpleJdbcCall(dataSource)
			.withProcedureName("MPG_K_EX_SINIESTRO_ACCIDENTE.PR_PROCESA_PETICION")
			.withCatalogName("MPD_LD")
            .withoutProcedureColumnMetaDataAccess()
            .useInParameterNames("P_R_SINIESTRO_ACC")
			.declareParameters(
				new SqlParameter("P_R_SINIESTRO_ACC", OracleTypes.STRUCT, "T_R_SINIESTRO_ACC"),
				new SqlOutParameter("O_NUM_EXPEDIENTE", Types.VARCHAR),
				new SqlOutParameter("O_COD_ERROR", Types.VARCHAR),
				new SqlOutParameter("O_TXT_ERROR", Types.VARCHAR)
			);
		//@formatter:on
	}

	@Override
	public OSinAccOutS execute(OSinAccInS claim) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("P_R_SINIESTRO_ACC", claim);
		Map<String, Object> response = jdbcCall.execute(in);
		log.info("Result: {}", response);
		throw new NotImplementedException("Not implemented");
	}

}
