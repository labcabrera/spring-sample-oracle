package org.lab.samples.pljpa.service.participant.oracle;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.lab.samples.pljpa.model.Participant;
import org.lab.samples.pljpa.oracle.CustomStructMapper;
import org.lab.samples.pljpa.oracle.StructDefinitionService;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class ParticipantOracleUpdateProcedure extends StoredProcedure {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_upd";

	private final CustomStructMapper<Participant> structMapper;

	public ParticipantOracleUpdateProcedure(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new CustomStructMapper<>(Participant.class, definitionService);

		SqlReturnStruct sqlReturn = new SqlReturnStruct(structMapper);

		setFunction(true);
		declareParameter(new SqlOutParameter("return", Types.STRUCT, "O_AMD_GNL_PAR_S", sqlReturn));
		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));
		declareParameter(new SqlParameter("p_usr_val", Types.VARCHAR));

		compile();
	}

	public Participant update(Participant entity, String username) {
		SqlStructValue<Participant> structValue = new SqlStructValue<>(entity, structMapper);

		Map<String, Object> resultMap = super.execute(structValue, username);
		return (Participant) resultMap.get("return");
	}

}
