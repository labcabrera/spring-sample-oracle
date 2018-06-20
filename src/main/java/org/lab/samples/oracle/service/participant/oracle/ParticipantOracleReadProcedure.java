package org.lab.samples.oracle.service.participant.oracle;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.SqlListStructArray;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jdbc.support.oracle.SqlReturnArray;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class ParticipantOracleReadProcedure extends ParticipantStoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_get";

	public ParticipantOracleReadProcedure(DataSource dataSource, StructMapperService mapperService) {
		super(dataSource, FUNCTION_NAME, mapperService);
		SqlReturnArray sqlReturn = new SqlListStructArray<>(mapperService.mapper(Participant.class));
		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.ARRAY, Participant.ORACLE_LIST_NAME, sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, Participant.ORACLE_TYPE_NAME));
		compile();
	}

	@SuppressWarnings("unchecked")
	public List<Participant> find(Participant beanQuery) {
		StructMapper<Participant> mapper = mapperService.mapper(Participant.class);
		SqlStructValue<Participant> structValue = new SqlStructValue<>(beanQuery, mapper);
		Map<String, Object> resultMap = super.execute(structValue);
		return (List<Participant>) resultMap.get("return");
	}

}
