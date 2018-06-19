package org.lab.samples.oracle.config;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	Docket docket() {
		return new Docket(SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.lab")).build();
	}

}
