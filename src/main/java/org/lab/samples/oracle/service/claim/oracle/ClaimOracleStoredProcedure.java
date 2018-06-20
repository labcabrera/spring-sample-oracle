package org.lab.samples.oracle.service.claim.oracle;

import java.sql.Types;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.CustomStructMapper;
import org.lab.samples.oracle.jdbc.StructDefinitionService;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClaimOracleStoredProcedure extends StoredProcedure {

	private static final String SPROC_NAME = "MPG_K_EX_SINIESTRO_ACC_WRP.P_PROCESA_PETICION";

	private final StructMapper<OSinAccInS> requestMapper;

	public ClaimOracleStoredProcedure(DataSource dataSource, StructDefinitionService definitionService,
		StructMapperService mapperService) {

		super(dataSource, SPROC_NAME);
		this.requestMapper = new CustomStructMapper<OSinAccInS>(OSinAccInS.class, definitionService, mapperService);
		SqlReturnStruct sqlReturn = new SqlReturnStruct(OSinAccOutS.class);
		declareParameter(new SqlParameter("P_O_SIN_ACC_IN_S", Types.STRUCT, "O_SIN_ACC_IN_S"));
		declareParameter(new SqlOutParameter("return", Types.STRUCT, "O_SIN_ACC_OUT_S", sqlReturn));
		compile();
	}

	public OSinAccOutS executeProc(Object... inParams) {
		OSinAccInS request = (OSinAccInS) inParams[0];
		Map in = Collections.singletonMap("P_O_SIN_ACC_IN_S", new SqlStructValue(request, requestMapper));
		Map results = super.execute(in);
		return (OSinAccOutS) results.get("return");
	}

}
