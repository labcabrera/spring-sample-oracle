package org.lab.samples.oracle.jdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.StructDescriptor;

/**
 * Componente encargado de almacenar en memoria la definicion de las estructuras de datos de Oracle para que no sea
 * necesario una llamada a la base de datos cada vez que se tiene que realizar una transformacion de STRUCT a una
 * entidad o de una entidad a un STRUCT.
 * 
 */
// TODO estudiar estrategias para almacenar el descriptor: cache, ficheros , etc.
// TODO estudiar si se desea separar la parte de generacion de los ficheros de serializados o si es mejor generarlos una
// unica vez bajo demanda cuando se solicita por primera vez un tipo dado.
@Component
@Slf4j
public class StructDefinitionService {

	private final Map<String, StructDescriptor> values;

	public StructDefinitionService() {
		values = new HashMap<>();
	}

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
