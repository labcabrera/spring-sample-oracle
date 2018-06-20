package org.lab.samples.oracle.service.claim.spring;

import java.sql.Connection;
import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.NotImplementedException;
import org.lab.samples.oracle.jdbc.StructDefinitionService;
import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Service
@Slf4j
public class ClaimSpringJdbcService implements ClaimService {

	private final SimpleJdbcCall jdbcCall;
	private final StructDefinitionService definitionService;

	public ClaimSpringJdbcService(DataSource dataSource, StructDefinitionService definitionService) {
		this.definitionService = definitionService;
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
	@SuppressWarnings("rawtypes")
	public OSinAccOutS execute(OSinAccInS claim) {
		try {
			Connection connection = jdbcCall.getJdbcTemplate().getDataSource().getConnection();

			StructDescriptor descriptor = definitionService.get("T_R_SINIESTRO_ACC", connection);

			ObjectMapper mapper = new ObjectMapper();
			Map map = mapper.convertValue(claim, Map.class);

			STRUCT request = new STRUCT(descriptor, connection, map);

			SqlParameterSource in = new MapSqlParameterSource().addValue("P_R_SINIESTRO_ACC", request);
			Map<String, Object> response = jdbcCall.execute(in);
			log.info("Result: {}", response);
			throw new NotImplementedException("Not implemented");
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
