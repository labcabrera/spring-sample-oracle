package org.lab.samples.oracle.jdbc;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.lab.samples.oracle.annotation.OracleStruct;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Slf4j
// TODO definir sistema de lectura de propiedades basado en anotaciones en lugar de leer el descriptor de oracle a
// traves de la conexion (tanto en el fromStruct como en el toStruct)
public class CustomStructMapper<T> extends BeanPropertyStructMapper<T> {

	private final StructDefinitionService definitionService;

	public CustomStructMapper(Class<T> mappedClass, StructDefinitionService ds) {
		super(mappedClass);
		this.definitionService = ds;
	}

	@Override
	public STRUCT toStruct(T source, Connection conn, String typeName) throws SQLException {
		Map<String, PropertyDescriptor> mappedFields = getMappedFields();

		StructDescriptor descriptor = definitionService.get(typeName, conn);
		ResultSetMetaData rsmd = descriptor.getMetaData();
		int columns = rsmd.getColumnCount();
		Object[] values = new Object[columns];
		for (int i = 1; i <= columns; i++) {
			String column = JdbcUtils.lookupColumnName(rsmd, i).toLowerCase();
			
			
			
			PropertyDescriptor fieldMeta = (PropertyDescriptor) mappedFields.get(column);
			if (fieldMeta != null) {
				BeanWrapper bw = new BeanWrapperImpl(source);
				if (bw.isReadableProperty(fieldMeta.getName())) {
					try {
						if (log.isDebugEnabled()) {
							log.debug("Mapping column named \"" + column + "\"" + " to property \""
								+ fieldMeta.getName() + "\"");
						}
						values[i - 1] = bw.getPropertyValue(fieldMeta.getName());
					}
					catch (NotReadablePropertyException ex) {
						throw new DataRetrievalFailureException(
							"Unable to map column " + column + " to property " + fieldMeta.getName(), ex);
					}
				}
				else {
					log.warn("Unable to access the getter for " + fieldMeta.getName() + ".  Check that " + "get"
						+ StringUtils.capitalize(fieldMeta.getName()) + " is declared and has public access.");
				}
			}
		}

		// Modified from spring-data-oracle to recursive struct conversion
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				continue;
			}
			Object obj = values[i];
			OracleStruct oracleAnnotation = obj.getClass().getAnnotation(OracleStruct.class);
			if (oracleAnnotation == null) {
				continue;
			}
			String typename = oracleAnnotation.value();
			log.info(String.format("Mapping %s to Oracle STRUCT %s", obj.getClass().getSimpleName(), typename));
			STRUCT tmp = toStruct(source, conn, typename);
			values[i] = tmp;
		}
		return new STRUCT(descriptor, conn, values);
	}
}