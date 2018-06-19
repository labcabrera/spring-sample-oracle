package org.lab.samples.oracle.service.participant.oracle;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.CustomStructMapper;
import org.lab.samples.oracle.internal.SqlListStructArray;
import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jdbc.support.oracle.SqlReturnArray;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class ParticipantOracleReadProcedure extends StoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_get";

	private final CustomStructMapper<Participant> structMapper;

	public ParticipantOracleReadProcedure(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new CustomStructMapper<>(Participant.class, definitionService);

		SqlReturnArray sqlReturn = new SqlListStructArray<>(structMapper);

		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.ARRAY, "O_AMD_GNL_PAR_ST", sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));
		compile();
	}

	@SuppressWarnings("unchecked")
	public List<Participant> find(Participant beanQuery) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(beanQuery, structMapper);
		Map<String, Object> resultMap = super.execute(structValue);
		return (List<Participant>) resultMap.get("return");
	}

}
