package org.lab.samples.oracle.service.claim.oracle;

import java.sql.Types;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.CustomStructMapper;
import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.OSinAccInS;
import org.lab.samples.oracle.model.OSinAccOutS;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClaimOracleStoredProcedure extends StoredProcedure {

	private static final String SPROC_NAME = "MPG_K_EX_SINIESTRO_ACC_WRP.P_PROCESA_PETICION";

	private StructDefinitionService definitionService;

	public ClaimOracleStoredProcedure(DataSource dataSource, StructDefinitionService sds) {
		super(dataSource, SPROC_NAME);
		this.definitionService = sds;
		declareParameter(new SqlParameter("P_O_SIN_ACC_IN_S", Types.STRUCT, "O_SIN_ACC_IN_S"));
		declareParameter(new SqlOutParameter("P_O_SIN_ACC_OUT_S", Types.STRUCT, "O_SIN_ACC_OUT_S",
			new SqlReturnStruct(OSinAccOutS.class)));
		compile();
	}

	public OSinAccOutS executeProc(Object... inParams) {
		OSinAccInS request = (OSinAccInS) inParams[0];

		CustomStructMapper<OSinAccInS> structMapper = new CustomStructMapper<OSinAccInS>(OSinAccInS.class,
			definitionService);

		Map in = Collections.singletonMap("P_O_SIN_ACC_IN_S", new SqlStructValue(request, structMapper));
		Map results = super.execute(in);
		return (OSinAccOutS) results.get("P_O_SIN_ACC_OUT_S");
	}

}
