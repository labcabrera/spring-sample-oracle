package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.data.jdbc.support.oracle.GaiaStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.mapfre.dgtp.samples.pljpa.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.model.Participant;

public class ParticipantReadFunction extends StoredProcedure { // SqlFunction {

	// private static final String FUNCTION_NAME = "MPD_LD.dl_gnl_par.f_get";
	private static final String FUNCTION_NAME = "dl_gnl_par.f_get";

	private final StructDefinitionService definitionService;

	public ParticipantReadFunction(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.definitionService = definitionService;

		setFunction(true);

		declareParameter(new SqlOutParameter("lo_gnl_par_st", Types.STRUCT, "O_AMD_GNL_PAR_ST",
			new SqlReturnStruct(Participant.class)));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));

		compile();
	}

	public Object find(Participant beanQuery) {
		GaiaStructMapper<Participant> structMapper = new GaiaStructMapper<>(Participant.class, definitionService);
		SqlStructValue structValue = new SqlStructValue(beanQuery, structMapper);

		Object result = super.execute(structValue);
		return result;
	}

}
