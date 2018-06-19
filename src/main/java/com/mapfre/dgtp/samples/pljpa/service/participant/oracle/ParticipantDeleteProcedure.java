package com.mapfre.dgtp.samples.pljpa.service.participant.oracle;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.oracle.GaiaStructMapper;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;

public class ParticipantDeleteProcedure extends StoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_dlt";

	private final GaiaStructMapper<Participant> structMapper;

	public ParticipantDeleteProcedure(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new GaiaStructMapper<>(Participant.class, definitionService);

		SqlReturnStruct sqlReturn = new SqlReturnStruct(structMapper);

		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.STRUCT, "O_AMD_GNL_PAR_S", sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));

		compile();
	}

	public Participant delete(Participant entity) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(entity, structMapper);
		Map<String, Object> resultMap = super.execute(structValue);
		return (Participant) resultMap.get("return");
	}

}
