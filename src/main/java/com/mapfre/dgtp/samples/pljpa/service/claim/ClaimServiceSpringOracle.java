package com.mapfre.dgtp.samples.pljpa.service.claim;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.model.OSinAccInS;
import com.mapfre.dgtp.samples.pljpa.model.OSinAccOutS;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;

@Repository
public class ClaimServiceSpringOracle {

	private final SiniestrosStoredProcedure siniestrosStoredProcedure;

	public ClaimServiceSpringOracle(DataSource dataSource, StructDefinitionService sds) {
		siniestrosStoredProcedure = new SiniestrosStoredProcedure(dataSource, sds);
	}

	public OSinAccOutS pProcesaPeticion(OSinAccInS pOSinAccInS) {
		return siniestrosStoredProcedure.executeProc(pOSinAccInS);
	}
}
