package org.lab.samples.pljpa.service.claim.oracle;

import javax.sql.DataSource;

import org.lab.samples.pljpa.model.OSinAccInS;
import org.lab.samples.pljpa.model.OSinAccOutS;
import org.lab.samples.pljpa.oracle.StructDefinitionService;
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
