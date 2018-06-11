package com.mapfre.dgtp.samples.pljpa.rest;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.SiniestroAcc;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccidentService {

	@Autowired
	private DataSource dataSource;

	public String execute(SiniestroAcc siniestro) throws SQLException {
		log.info("Executing query");

		String sql = "{call RATELIMIT_OWN.unlimit_contract (?,?)}";
		CallableStatement callableStatement = dataSource.getConnection().prepareCall(sql);
		callableStatement.setInt(1, 0123);
		callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

		ResultSet resultSet = callableStatement.executeQuery();
		log.debug("");

		log.debug("ResultSet: {}", resultSet);

		return "TODO";
	}
}
