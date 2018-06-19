package org.lab.samples.oracle.service.claim.oracle;

import javax.sql.DataSource;

import org.lab.samples.oracle.internal.StructDefinitionService;
import org.lab.samples.oracle.model.OSinAccInS;
import org.lab.samples.oracle.model.OSinAccOutS;
import org.springframework.stereotype.Repository;

@Repository
public class ClaimOracleService {

	private final ClaimOracleStoredProcedure siniestrosStoredProcedure;

	public ClaimOracleService(DataSource dataSource, StructDefinitionService sds) {
		siniestrosStoredProcedure = new ClaimOracleStoredProcedure(dataSource, sds);
	}

	public OSinAccOutS pProcesaPeticion(OSinAccInS pOSinAccInS) {
		return siniestrosStoredProcedure.executeProc(pOSinAccInS);
	}
}
