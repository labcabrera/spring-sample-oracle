package com.mapfre.dgtp.samples.pljpa.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.SiniestroAcc;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccidentServiceJdbc implements AccidentService {

	@Autowired
	private DataSource dataSource;

	public String execute(SiniestroAcc siniestro) {
		try {
			log.info("Executing query");

			String sql = "{call MPD_LD.MPG_K_EX_SINIESTRO_ACCIDENTE (?,?,?,?)}";
			CallableStatement callableStatement = dataSource.getConnection().prepareCall(sql);

			// TODO no soporta esta conversion directamente
			callableStatement.setObject(1, siniestro);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);

			ResultSet resultSet = callableStatement.executeQuery();
			log.debug("ResultSet: {}", resultSet);

			return "TODO";

		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
