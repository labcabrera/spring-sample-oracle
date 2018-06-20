package org.lab.samples.oracle.config;

import org.lab.samples.oracle.jdbc.mapper.AnnotationStructMapperService;
import org.lab.samples.oracle.jdbc.mapper.CustomStructMapperService;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

	@Bean("mapper-annotation")
	StructMapperService mapperServiceAnnotation() {
		return new AnnotationStructMapperService();
	}

	@Bean("mapper-custom")
	StructMapperService mapperServiceCustom() {
		return new CustomStructMapperService();
	}

}
