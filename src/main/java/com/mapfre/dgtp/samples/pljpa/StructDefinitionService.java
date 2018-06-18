package com.mapfre.dgtp.samples.pljpa;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.StructDescriptor;

//TODO
@Component
@Slf4j
public class StructDefinitionService {

	private final Map<String, StructDescriptor> values;

	public StructDefinitionService() {
		values = new HashMap<>();
	}

	// TODO check serialization
	public StructDescriptor get(String typeName, Connection conn) {
		try {
			if (values.containsKey(typeName)) {
				return values.get(typeName);
			}
			else {
				log.info("Reading type {} descriptor", typeName);
				StructDescriptor desc = new StructDescriptor(typeName, conn);
				values.put(typeName, desc);
				return desc;
			}
		}
		catch (Exception ex) {
			throw new RuntimeException("Error reading type descriptor " + typeName, ex);
		}
	}

}
