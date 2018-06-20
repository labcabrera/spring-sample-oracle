package org.lab.samples.oracle.jdbc;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.lab.samples.oracle.annotation.OracleCollection;
import org.lab.samples.oracle.annotation.OracleStruct;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Slf4j
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
		StructDescriptor descriptor = definitionService.structDescriptor(typeName, conn);
		ResultSetMetaData rsmd = descriptor.getMetaData();
		int columns = rsmd.getColumnCount();
		Object[] values = new Object[columns];
		for (int i = 1; i <= columns; i++) {
			String column = JdbcUtils.lookupColumnName(rsmd, i).toLowerCase();
			PropertyDescriptor propertyDescriptor = (PropertyDescriptor) mappedFields.get(column);
			if (propertyDescriptor == null) {
				continue;
			}
			BeanWrapper bw = new BeanWrapperImpl(source);
			if (bw.isReadableProperty(propertyDescriptor.getName())) {
				try {
					log.debug("Mapping column named '{}' to property '{}'", column, propertyDescriptor.getName());
					Object target = bw.getPropertyValue(propertyDescriptor.getName());
					target = checkStructConversion(target, source, propertyDescriptor, conn);
					values[i - 1] = target;
				}
				catch (NotReadablePropertyException ex) {
					throw new DataRetrievalFailureException(
						"Unable to map column " + column + " to property " + propertyDescriptor.getName(), ex);
				}
			}
			else {
				log.warn("Unable to access the getter for {}. Check that get{} is declared and has public access.",
					propertyDescriptor.getName(), StringUtils.capitalize(propertyDescriptor.getName()));
			}

		}
		return new STRUCT(descriptor, conn, values);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object checkStructConversion(Object source, Object parent, PropertyDescriptor descriptor,
		Connection connection) {

		try {
			if (source != null) {
				if (List.class.isAssignableFrom(source.getClass())) {
					// En el caso de ser una coleccion determinamos el tipo a partir de la anotacion OracleCollection
					// declarada en el objeto que define la lista
					Assert.notNull(parent, "Parent object is required to determine @OracleCollection annotation");

					List list = (List) source;
					Object[] values = new Object[list.size()];
					for (int i = 0; i < list.size(); i++) {
						values[i] = checkStructConversion(list.get(i), source, descriptor, connection);
					}
					String collectionFieldName = descriptor.getName();
					Field collectionField = parent.getClass().getDeclaredField(collectionFieldName);
					OracleCollection annotation = collectionField.getAnnotation(OracleCollection.class);
					String collectionName = annotation.value();

					Assert.notNull(annotation, "Missing @OracleCollection on field " + collectionField + " in class "
						+ parent.getClass().getName());

					ArrayDescriptor arrayDescriptor = definitionService.arrayDescriptor(collectionName, connection);
					ARRAY oracleArray = new ARRAY(arrayDescriptor, connection, values);
					return oracleArray;
				}
				else if (source.getClass().getAnnotation(OracleStruct.class) != null) {
					String typeName = source.getClass().getAnnotation(OracleStruct.class).value();
					log.info("Mapping {} to Oracle STRUCT {}", source.getClass().getSimpleName(), typeName);
					StructMapper mapper = mapperService.mapper(source.getClass());
					return mapper.toStruct(source, connection, typeName);
				}
			}
			return source;
		}
		catch (Exception ex) {
			throw new RuntimeException("Error mapping " + source);
		}
	}

}