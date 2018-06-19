package org.lab.samples.pljpa.service.claim.spring;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.pljpa.model.Claim;
import org.lab.samples.pljpa.service.claim.ClaimService;
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
public class ClaimServiceJdbc implements ClaimService {

	private final SimpleJdbcCall jdbcCall;

	public ClaimServiceJdbc(DataSource dataSource) {
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

	public String execute(Claim siniestro) {
		log.info("Executing query");
		// TODO invalid conversion object to struct
		SqlParameterSource in = new MapSqlParameterSource().addValue("P_R_SINIESTRO_ACC", siniestro);
		Map<String, Object> response = jdbcCall.execute(in);
		log.info("Result: {}", response);
		return "TODO-JDBC";

	}

}
