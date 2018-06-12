package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Claim;
import com.mapfre.dgtp.samples.pljpa.model.OSinAccInS;
import com.mapfre.dgtp.samples.pljpa.model.OSinAccOutS;
import com.mapfre.dgtp.samples.pljpa.service.impl.ClaimServiceJdbc;
import com.mapfre.dgtp.samples.pljpa.service.impl.ClaimServiceJpa;
import com.mapfre.dgtp.samples.pljpa.service.impl.IDlMpgKExSiniestroAccWrpImpl;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	@Autowired
	private ClaimServiceJdbc serviceJdbc;

	@Autowired
	private ClaimServiceJpa serviceJpa;

	@Autowired
	private IDlMpgKExSiniestroAccWrpImpl idlImpl;

	@PostMapping("/jdbc")
	public String executeJdbc(Claim claim) {
		return serviceJdbc.execute(claim);
	}

	@PostMapping("/jpa")
	public String executeJpa(Claim claim) {
		return serviceJpa.execute(claim);
	}

	@PostMapping("/spring-oracle")
	public OSinAccOutS executeSpringOracle(OSinAccInS pOSinAccInS) {
		return idlImpl.pProcesaPeticion(pOSinAccInS);
	}

}
