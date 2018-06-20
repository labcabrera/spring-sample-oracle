package org.lab.samples.oracle.service.claim.oracle;

import javax.sql.DataSource;

import org.lab.samples.oracle.jdbc.StructDefinitionService;
import org.lab.samples.oracle.jdbc.mapper.StructMapperService;
import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ClaimOracleService implements ClaimService {

	private final ClaimOracleStoredProcedure siniestrosStoredProcedure;

	public ClaimOracleService( //@formatter:off
		DataSource dataSource,
		StructDefinitionService definitionService,
		@Qualifier("mapper-custom") StructMapperService mapperService) { //@formatter:on

		siniestrosStoredProcedure = new ClaimOracleStoredProcedure(dataSource, definitionService, mapperService);
	}

	@Override
	public OSinAccOutS execute(OSinAccInS pOSinAccInS) {
		return siniestrosStoredProcedure.executeProc(pOSinAccInS);
	}
}
