package com.mapfre.dgtp.samples.pljpa.service.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.model.OSinAccInS;
import com.mapfre.dgtp.samples.pljpa.model.OSinAccOutS;

/**
 * User: jose Date: 13/11/17 Time: 12:16
 */
@Repository
@Primary
public class IDlMpgKExSiniestroAccWrpImpl {

	private SiniestrosStoredProcedure siniestrosStoredProcedure;

	@Autowired
	public IDlMpgKExSiniestroAccWrpImpl(DataSource dataSource) {
		siniestrosStoredProcedure = new SiniestrosStoredProcedure(dataSource);
	}

	/**
	 * pProcesaPeticion DL Operation
	 *
	 * @param pOSinAccInS pOSinAccInS DL parameter
	 * @return OSinAccOutS
	 */
	public OSinAccOutS pProcesaPeticion(OSinAccInS pOSinAccInS) {
		return siniestrosStoredProcedure.executeProc(pOSinAccInS);
	}
}
