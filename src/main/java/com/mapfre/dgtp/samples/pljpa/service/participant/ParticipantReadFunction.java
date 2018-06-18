package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.data.jdbc.support.oracle.GaiaStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlReturnArray;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.mapfre.dgtp.samples.pljpa.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.model.Participant;

import lombok.AllArgsConstructor;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

public class ParticipantReadFunction extends StoredProcedure { // SqlFunction {

	private static final String FUNCTION_NAME = "dl_gnl_par.f_get";

	private final GaiaStructMapper<Participant> structMapper;

	public ParticipantReadFunction(DataSource dataSource, StructDefinitionService definitionService) {
		super(dataSource, FUNCTION_NAME);
		this.structMapper = new GaiaStructMapper<>(Participant.class, definitionService);

		setFunction(true);

		declareParameter(
			new SqlOutParameter("return", Types.ARRAY, "O_AMD_GNL_PAR_ST", new ParticipantReturnArray(structMapper)));

		declareParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.STRUCT, "O_AMD_GNL_PAR_S"));

		compile();
	}

	public Object find(Participant beanQuery) {
		try {
			SqlStructValue structValue = new SqlStructValue(beanQuery, structMapper);
			Map<String, Object> resultMap = super.execute(structValue);
			return resultMap.get("return");
			//
			// List<Participant> list = new ArrayList<>();
			//
			// for (int i = 0; i < tmp.length; i++) {
			// STRUCT s = (STRUCT) tmp[i];
			// Participant bean = structMapper.fromStruct(s);
			// list.add(bean);
			// }
			// return list;
		}
		catch (Exception ex) {
			throw new RuntimeException("Participant search error", ex);
		}

	}

	@AllArgsConstructor
	public static class ParticipantReturnArray extends SqlReturnArray {

		private final StructMapper mapper;

		public Object getTypeValue(CallableStatement cs, int i, int sqlType, String typeName) throws SQLException {
			ARRAY array = (ARRAY) cs.getObject(i);
			if (array == null) {
				return null;
			}
			Object[] values = (Object[]) array.getArray();
			List<Participant> list = new ArrayList<>();
			for (int z = 0; z < values.length; z++) {
				STRUCT struct = (STRUCT) values[z];
				Participant p = (Participant) mapper.fromStruct(struct);
				list.add(p);
			}
			return list;
		}

	}

}
