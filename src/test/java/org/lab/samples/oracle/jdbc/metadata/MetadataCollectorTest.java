package org.lab.samples.oracle.jdbc.metadata;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lab.samples.oracle.jdbc.metadata.model.OracleMappingData;
import org.lab.samples.oracle.jdbc.metadata.model.OracleMappingStructData;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import oracle.jdbc.pool.OracleDataSource;

@RunWith(SpringRunner.class)
public class MetadataCollectorTest {

	private MetadataCollector collector;

	// TODO use SpringBootTest instead create instance manually
	@Before
	public void before() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL("jdbc:oracle:thin:@vles044273-011:1521:OBRDVL");
		dataSource.setUser("MPD_LD");
		dataSource.setPassword("MPD_LD");
		collector = new MetadataCollector(dataSource);
	}

	@Test
	public void test() throws Exception {
		OracleMappingData readMetadata = collector.readMetadata("org.lab");

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		String json = mapper.writeValueAsString(readMetadata);
		System.out.println(json);

		System.out.println("--- unmapped fields ---");
		for (OracleMappingStructData i : readMetadata.getStructs()) {
			i.getFields().stream().filter(x -> !x.getMapped()).forEach(System.out::println);
		}
		System.out.println("-- /unmapped fields ---");
	}

}
