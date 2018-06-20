package org.lab.samples.oracle.jdbc.mapper;

import org.lab.samples.oracle.jdbc.CustomStructMapper;
import org.lab.samples.oracle.jdbc.StructDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.stereotype.Component;

public class CustomStructMapperService implements StructMapperService {

	@Autowired
	private StructDefinitionService definitionService;

	@Override
	public <T> StructMapper<T> mapper(Class<T> mappedClass) {
		return new CustomStructMapper<>(mappedClass, definitionService);
	}

}