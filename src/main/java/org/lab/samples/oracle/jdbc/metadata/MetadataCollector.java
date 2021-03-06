package org.lab.samples.oracle.jdbc.metadata;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.lab.samples.oracle.annotation.OracleCollection;
import org.lab.samples.oracle.annotation.OracleField;
import org.lab.samples.oracle.annotation.OracleStruct;
import org.lab.samples.oracle.jdbc.metadata.model.OracleMappingData;
import org.lab.samples.oracle.jdbc.metadata.model.OracleMappingField;
import org.lab.samples.oracle.jdbc.metadata.model.OracleMappingStructData;
import org.reflections.Reflections;

import com.fasterxml.jackson.databind.type.CollectionLikeType;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.StructDescriptor;

@Slf4j
public class MetadataCollector {

	private final DataSource dataSource;

	public MetadataCollector(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public OracleMappingData readMetadata(String packageName) throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			return readMetadata(packageName, connection);
		}
	}

	public OracleMappingData readMetadata(String packageName, Connection connection) throws SQLException {

		Set<Class<?>> structs = new Reflections(packageName).getTypesAnnotatedWith(OracleStruct.class);

		OracleMappingData result = new OracleMappingData();
		result.setPackageName(packageName);

		for (Class<?> structClass : structs) {
			log.info("Loading class {}", structClass.getName());

			result.register(loadStructData(structClass, connection));

		}
		return result;

	}

	private OracleMappingStructData loadStructData(Class<?> structClass, Connection connection) throws SQLException {
		OracleMappingStructData result = new OracleMappingStructData();
		OracleStruct annotation = structClass.getAnnotation(OracleStruct.class);
		String structName = annotation.value();
		mapOracleMetaData(result, structName, connection);
		mapFields(result, structClass);
		result.setMappedClass(structClass);
		result.setStrucyName(annotation.value());
		return result;
	}

	private void mapOracleMetaData(OracleMappingStructData result, String structName, Connection connection)
		throws SQLException {
		StructDescriptor desc = new StructDescriptor(structName, connection);
		ResultSetMetaData metaData = desc.getMetaData();
		int count = metaData.getColumnCount();
		for (int i = 0; i < count; i++) {
			OracleMappingField field = new OracleMappingField();
			field.setMapped(false);
			field.setOracleColumnName(metaData.getColumnName(i + 1));
			field.setOracleTypeName(metaData.getColumnTypeName(i + 1));
			field.setOracleColumnClassName(metaData.getColumnClassName(i + 1));
			field.setOracleSchemaName(metaData.getSchemaName(i + 1));
			result.registerField(field);
		}
	}

	private void mapFields(OracleMappingStructData data, Class<?> structClass) {
		for (Field field : structClass.getDeclaredFields()) {

			String fieldName = field.getName();
			OracleField oracleField = field.getAnnotation(OracleField.class);
			OracleCollection oracleCollection = field.getAnnotation(OracleCollection.class);

			OracleMappingField target = null;

			UnaryOperator<String> nameNormalizer = x -> x.toUpperCase().replaceAll("_", "");

			String fieldNameMatch = nameNormalizer.apply(fieldName);
			Predicate<OracleMappingField> fieldPredicate = x -> fieldNameMatch
				.equals(nameNormalizer.apply(x.getOracleColumnName()));

			if (oracleCollection != null) {
				String collectionName = oracleCollection.value();
				log.debug("Mapping field '{}' as a collection '{}'", fieldName, collectionName);

				List<OracleMappingField> collect = data.getFields().stream().filter(fieldPredicate)
					.collect(Collectors.toList());

				switch (collect.size()) {
				case 1:
					target = collect.iterator().next();
					log.debug("Oracle bind {}", target.getOracleColumnName());
					bindFieldInfo(target, field);
					target.setMapped(true);
					break;
				case 0:
					OracleMappingField newMapping = new OracleMappingField();
					bindFieldInfo(newMapping, field);
					data.registerField(newMapping);
					break;
				default:
					throw new RuntimeException("Multiple candidates for field " + field.getName() + "("
						+ field.getDeclaringClass().getName() + ")");
				}

				bindFieldInfo(target, field);
				target.setMapped(true);

				// TODO map collection info
			}
			else if (oracleField != null) {
				String oracleName = oracleField.value();
				log.debug("Mapping field '{}' as '{}'", fieldName, oracleName);
			}
			else {
				log.debug("Mapping field as default");

				List<OracleMappingField> collect = data.getFields().stream().filter(fieldPredicate)
					.collect(Collectors.toList());

				switch (collect.size()) {
				case 1:
					target = collect.iterator().next();
					log.debug("Oracle bind {}", target.getOracleColumnName());
					bindFieldInfo(target, field);
					target.setMapped(true);
					break;
				case 0:
					OracleMappingField newMapping = new OracleMappingField();
					bindFieldInfo(newMapping, field);
					data.registerField(newMapping);
					break;
				default:
					throw new RuntimeException("Multiple candidates for field " + field.getName() + "("
						+ field.getDeclaringClass().getName() + ")");
				}

			}

		}
	}

	private void bindFieldInfo(OracleMappingField mapping, Field field) {
		mapping.setJavaAttributeName(field.getName());

	}

}
