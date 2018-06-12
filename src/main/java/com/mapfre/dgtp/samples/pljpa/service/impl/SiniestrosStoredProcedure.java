package com.mapfre.dgtp.samples.pljpa.service.impl;

import java.sql.Types;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.mapfre.dgtp.samples.pljpa.model.OSinAccInS;
import com.mapfre.dgtp.samples.pljpa.model.OSinAccOutS;

/**
 * User: jose Date: 13/11/17 Time: 13:03
 */
public class SiniestrosStoredProcedure extends StoredProcedure {

	private static final String SPROC_NAME = "MPG_K_EX_SINIESTRO_ACC_WRP.P_PROCESA_PETICION";

	public SiniestrosStoredProcedure(DataSource dataSource) {
		super(dataSource, SPROC_NAME);
		declareParameter(new SqlParameter("P_O_SIN_ACC_IN_S", Types.STRUCT, "O_SIN_ACC_IN_S"));
		declareParameter(new SqlOutParameter("P_O_SIN_ACC_OUT_S", Types.STRUCT, "O_SIN_ACC_OUT_S",
			new SqlReturnStruct(OSinAccOutS.class)));
		compile();
	}

	public OSinAccOutS executeProc(Object... inParams) {
		Map in = Collections.singletonMap("P_O_SIN_ACC_IN_S", new SqlStructValue((OSinAccInS) inParams[0]));
		Map results = super.execute(in);
		return (OSinAccOutS) results.get("P_O_SIN_ACC_OUT_S");
	}

}
