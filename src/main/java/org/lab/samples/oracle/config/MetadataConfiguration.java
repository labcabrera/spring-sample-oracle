package org.lab.samples.oracle.config;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.metadata.MetadataCollector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetadataConfiguration {

	@ConditionalOnMissingBean(MetadataCollector.class)
	@Bean
	MetadataCollector metadataCollector(DataSource dataSource) {
		return new MetadataCollector(dataSource);
	}

}
