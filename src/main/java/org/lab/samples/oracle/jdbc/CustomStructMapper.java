package org.lab.samples.oracle.jdbc;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.lab.samples.oracle.annotation.OracleStruct;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.StructMapper;
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
	private final StructMapperService mapperService;

	public CustomStructMapper(Class<T> mappedClass, StructDefinitionService ds, StructMapperService mapperService) {
		super(mappedClass);
		this.definitionService = ds;
		this.mapperService = mapperService;
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
							log.debug("Mapping column named '{}' to property '{}'", column, fieldMeta.getName());
						}
						values[i - 1] = bw.getPropertyValue(fieldMeta.getName());
					}
					catch (NotReadablePropertyException ex) {
						throw new DataRetrievalFailureException(
							"Unable to map column " + column + " to property " + fieldMeta.getName(), ex);
					}
				}
				else {
					log.warn("Unable to access the getter for {}. Check that get{} is declared and has public access.",
						fieldMeta.getName(), StringUtils.capitalize(fieldMeta.getName()));
				}
			}
		}
		// Modified from spring-data-oracle to recursive STRUCT conversion
		try {
			recursiveStructConversion(values, conn);
			return new STRUCT(descriptor, conn, values);
		}
		catch (Exception ex) {
			throw new SQLException("Oracle STRUCT conversion error using mappedClass " + mappedClass.getName(), ex);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void recursiveStructConversion(Object[] values, Connection connection) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			Object source = values[i];
			if (source != null) {
				if (source.getClass().getAnnotation(OracleStruct.class) != null) {
					Object resolved = checkStructConversion(source, connection);
					values[i] = resolved;
				}
				else if (List.class.isAssignableFrom(source.getClass())) {
					List list = (List) source;
					for (int index = 0; index < list.size(); index++) {
						list.set(index, checkStructConversion(list.get(index), connection));
					}
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object checkStructConversion(Object source, Connection connection) throws SQLException {
		if (source != null) {
			String typeName;
			if (source.getClass().getAnnotation(OracleStruct.class) != null) {
				typeName = source.getClass().getAnnotation(OracleStruct.class).value();
				log.info("Mapping {} to Oracle STRUCT {}", source.getClass().getSimpleName(), typeName);
				StructMapper mapper = mapperService.mapper(source.getClass());
				return mapper.toStruct(source, connection, typeName);
			}
		}
		return source;
	}

}