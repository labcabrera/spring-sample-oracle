package org.lab.samples.oracle.service.participant.oracle;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.CustomStructMapper;
import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class ParticipantOracleInsertProcedure extends StoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_inr";

	private final CustomStructMapper<Participant> structMapper;

	public ParticipantOracleInsertProcedure(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new CustomStructMapper<>(Participant.class, definitionService);

		SqlReturnStruct sqlReturn = new SqlReturnStruct(structMapper);

		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.STRUCT, Participant.ORACLE_TYPE_NAME, sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, Participant.ORACLE_TYPE_NAME));
		declareParameter(new SqlParameter("p_usr_val", Types.VARCHAR));

		compile();
	}

	public Participant insert(Participant entity, String username) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(entity, structMapper);

		Map<String, Object> resultMap = super.execute(structValue, username);
		return (Participant) resultMap.get("return");
	}

}
