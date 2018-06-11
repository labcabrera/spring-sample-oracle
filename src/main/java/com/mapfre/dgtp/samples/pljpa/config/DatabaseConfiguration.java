package com.mapfre.dgtp.samples.pljpa.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.mapfre.dgtp.samples.pljpa.repositories")
@Slf4j
public class DatabaseConfiguration {

	@Value("${app.env.datasource.url}")
	private String url;

	@Value("${app.env.datasource.username}")
	private String userName;

	@Value("${app.env.datasource.password}")
	private String password;

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
