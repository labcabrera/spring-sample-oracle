package org.lab.samples.oracle.service.claim.oracle;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.participant.claim.OSinAccInS;
import org.lab.samples.oracle.model.participant.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.stereotype.Repository;

@Repository
public class ClaimOracleService implements ClaimService {

	private final ClaimOracleStoredProcedure siniestrosStoredProcedure;

	public ClaimOracleService(DataSource dataSource, StructDefinitionService definitionService) {
		siniestrosStoredProcedure = new ClaimOracleStoredProcedure(dataSource, definitionService);
	}

	public OSinAccOutS execute(OSinAccInS pOSinAccInS) {
		return siniestrosStoredProcedure.executeProc(pOSinAccInS);
	}
}
