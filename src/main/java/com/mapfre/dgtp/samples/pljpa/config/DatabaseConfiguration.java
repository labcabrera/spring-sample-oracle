package com.mapfre.dgtp.samples.pljpa.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@Slf4j
public class DatabaseConfiguration {

	// TODO configuration
	private String url = "jdbc:oracle:thin:@vles044273-011:1521:OBRDVL";
	private String userName = "MPD_LD";
	private String password = "MPD_LD";

	@Bean
	DataSource dataSource() throws SQLException {
		log.debug("Generating datasource {}", url);
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL(url);
		dataSource.setUser(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

}
