package com.mapfre.dgtp.samples.pljpa.service.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.service.ParticipantService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Service
@Slf4j
public class ParticipantServiceJdbcBasic implements ParticipantService {

	private final DataSource dataSource;

	public ParticipantServiceJdbcBasic(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private BigDecimal add() throws SQLException {
		Connection connection = dataSource.getConnection();
		String call = "{ ? = call dl_gnl_par.f_add(?,?) }";
		CallableStatement statement = connection.prepareCall(call);
		statement.registerOutParameter(1, OracleTypes.NUMBER);
		statement.setObject(2, OracleTypes.NUMBER);
		statement.setObject(3, OracleTypes.NUMBER);
		statement.execute();
		BigDecimal result = statement.getObject(1, BigDecimal.class);
		log.info("Result: {}", result);
		return result;
	}

	@Override
	public List<Participant> findAll() {
		log.info("Executing query");

		try {
			add();

			Connection connection = dataSource.getConnection();

			StructDescriptor descriptor = StructDescriptor.createDescriptor("O_AMD_GNL_PAR_S", connection);
			Object[] args = new Object[] { null, null, null };
			STRUCT struct = new STRUCT(descriptor, connection, args);

			String call = "{ ? = call dl_gnl_par.f_get(?) }";
			CallableStatement statement = connection.prepareCall(call);
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.setObject(2, struct);

			statement.execute();
			// Assert.isTrue(check, "Execution error");

			ResultSet rs = statement.executeQuery();
			log.info("Result: {}", rs);

			while (rs.next()) {
				Object object = rs.getObject(1);
				System.out.println(object);
			}

		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return null;
	}
}
