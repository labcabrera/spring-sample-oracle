package org.lab.samples.oracle.service.participant.oracle;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class ParticipantOracleDeleteProcedure extends ParticipantStoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_dlt";

	public ParticipantOracleDeleteProcedure(DataSource dataSource, StructMapperService mapperService) {
		super(dataSource, FUNCTION_NAME, mapperService);
		SqlReturnStruct sqlReturn = new SqlReturnStruct(mapperService.mapper(Participant.class));
		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.STRUCT, Participant.ORACLE_TYPE_NAME, sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, Participant.ORACLE_TYPE_NAME));
		compile();
	}

	public Participant delete(Participant entity) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(entity, mapperService.mapper(Participant.class));
		Map<String, Object> resultMap = super.execute(structValue);
		return (Participant) resultMap.get("return");
	}

}
