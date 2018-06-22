package org.lab.samples.oracle.jdbc.metadata.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OracleMappingField {

	private String javaAttributeName;

	private String oracleColumnName;
	private String oracleTypeName;
	private String oracleColumnClassName;
	private String oracleSchemaName;

	private Boolean mapped;
}
