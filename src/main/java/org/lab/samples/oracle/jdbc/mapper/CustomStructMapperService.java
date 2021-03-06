package org.lab.samples.oracle.jdbc.mapper;

import org.lab.samples.oracle.jdbc.CustomStructMapper;
import org.lab.samples.oracle.jdbc.StructDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.StructMapper;

public class CustomStructMapperService implements StructMapperService {

	@Autowired
	private StructDefinitionService definitionService;

	@Autowired
	private StructMapperService mapperService;

	@Override
	public <T> StructMapper<T> mapper(Class<T> mappedClass) {
		return new CustomStructMapper<>(mappedClass, definitionService, mapperService);
	}

}
