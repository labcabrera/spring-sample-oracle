package org.lab.samples.oracle.service.participant.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.lab.samples.oracle.model.participant.Participant;
import org.lab.samples.oracle.service.participant.ParticipantService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ParticipantSpringJdbcService implements ParticipantService {

	private final SimpleJdbcCall jdbcCall;

	public ParticipantSpringJdbcService(DataSource dataSource) {
		//@formatter:off
		jdbcCall = new SimpleJdbcCall(dataSource)
			.withFunctionName("dl_gnl_par.f_get")
			.withCatalogName("MPD_LD")
			//.useInParameterNames("p_o_amd_gnl_par_s")
			//.withoutProcedureColumnMetaDataAccess()
			.returningResultSet("O_AMD_GNL_PAR_ST", new RowMapper() {

				@Override
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					throw new RuntimeException("Not implemented");
				}
				
			});
//		
//		jdbcCall.addDeclaredParameter(new SqlParameter("p_o_amd_gnl_par_s", Types.JAVA_OBJECT, "p_o_amd_gnl_par_s"));
//		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_o_amd_gnl_par_s", OracleTypes.CURSOR, new RowMapper() {
//				@Override
//				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//					throw new RuntimeException("Not implemented");
//				}
//			})
//		);
		//@formatter:on

	}

	@SuppressWarnings("unused")
	@Override
	public List<Participant> findAll() {
		log.info("Executing query");

		Participant queryObject = new Participant();
		// queryObject.setId(1L);

		try {
			Connection connection = jdbcCall.getJdbcTemplate().getDataSource().getConnection();
			StructDescriptor descriptor = StructDescriptor.createDescriptor("O_AMD_GNL_PAR_S", connection);
			Object[] args = new Object[] { null, null, null };
			STRUCT struct = new STRUCT(descriptor, connection, args);

			SqlParameterSource in = new MapSqlParameterSource().addValue("p_o_amd_gnl_par_s", struct);

			// TODO invalid conversion object to struct

			Object response = jdbcCall.executeFunction(Object.class, in);
			log.info("Result: {}", response);
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return null;
	}

	@Override
	public List<Participant> find(Participant beanQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant insert(Participant entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant update(Participant entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public void addToCallableStatement(CallableStatement callable, OracleConnection connection,
	// ParameterMetadata parameterMetadata, Object value, int position) {
	// try {
	// ObjectMetadata objectMetadata = this.objectManager.getValue(parameterMetadata.getClassType());
	// if (value != null) {
	// StructDescriptor objectDescriptor = StructDescriptor.createDescriptor(objectMetadata.getStructName(),
	// connection);
	// Object[] arrayObjectsValue = new ParamObjectProcessor(connection).process(value);
	// callable.setObject(position, new STRUCT(objectDescriptor, connection, arrayObjectsValue));
	// }
	// else {
	// callable.setNull(position, java.sql.Types.STRUCT, objectMetadata.getStructName());
	// }
	// }
	// catch (SQLException e) {
	// throw new MetadataConnectorException(MetadataError.OBJECT_IN_PARAM, e);
	// }
	//
	// }
}
