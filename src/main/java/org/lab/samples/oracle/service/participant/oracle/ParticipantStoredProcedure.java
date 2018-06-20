package org.lab.samples.oracle.service.participant.oracle;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.springframework.jdbc.object.StoredProcedure;

public class ParticipantStoredProcedure extends StoredProcedure {

	protected StructMapperService mapperService;

	public ParticipantStoredProcedure(DataSource dataSource, String storedProcedureName,
		StructMapperService mapperService) {

		super(dataSource, storedProcedureName);
		this.mapperService = mapperService;
	}

}
