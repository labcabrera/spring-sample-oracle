package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.data.jdbc.support.oracle.GaiaStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlReturnArray;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.oracle.SqlListStructArray;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;

public class ParticipantReadFunction extends StoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_get";

	private final GaiaStructMapper<Participant> structMapper;

	public ParticipantReadFunction(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new GaiaStructMapper<>(Participant.class, definitionService);

		SqlReturnArray sqlReturn = new SqlListStructArray<>(structMapper);

		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.ARRAY, "O_AMD_GNL_PAR_ST", sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));
		compile();
	}

	public Object find(Participant beanQuery) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(beanQuery, structMapper);
		Map<String, Object> resultMap = super.execute(structValue);
		return resultMap.get("return");
	}

}
