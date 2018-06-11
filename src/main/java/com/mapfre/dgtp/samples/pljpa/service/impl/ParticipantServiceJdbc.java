package com.mapfre.dgtp.samples.pljpa.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;

@Service
@Slf4j
public class ParticipantServiceJdbc implements ParticipantService {

	private final SimpleJdbcCall jdbcCall;

	public ParticipantServiceJdbc(DataSource dataSource) {
		//@formatter:off
		jdbcCall = new SimpleJdbcCall(dataSource)
			.withFunctionName("dl_gnl_par.f_get")
			.withCatalogName("MPD_LD")
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(
				new SqlParameter("p_o_amd_gnl_par_s", Types.JAVA_OBJECT),
				new SqlOutParameter("p_o_amd_gnl_par_s", OracleTypes.CURSOR, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						throw new RuntimeException("Not implemented");
					}
				})
			);
		//@formatter:on
	}

	@Override
	public List<Participant> findAll() {
		log.info("Executing query");

		Participant queryObject = new Participant();
		queryObject.setId(1L);

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_o_amd_gnl_par_s ", queryObject);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		// TODO invalid conversion object to struct

		String response = jdbcCall.executeFunction(String.class, in);
		log.info("Result: {}", response);
		return null;
	}
}
