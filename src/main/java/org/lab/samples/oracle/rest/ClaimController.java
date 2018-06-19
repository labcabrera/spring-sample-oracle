package org.lab.samples.oracle.rest;

import org.lab.samples.oracle.model.Claim;
import org.lab.samples.oracle.model.OSinAccInS;
import org.lab.samples.oracle.model.OSinAccOutS;
import org.lab.samples.oracle.service.claim.jpa.ClaimServiceJpa;
import org.lab.samples.oracle.service.claim.oracle.ClaimOracleService;
import org.lab.samples.oracle.service.claim.spring.ClaimServiceJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	@Autowired
	private ClaimServiceJdbc serviceJdbc;

	@Autowired
	private ClaimServiceJpa serviceJpa;

	@Autowired
	private ClaimOracleService serviceSpringOracle;

	@PostMapping("/jdbc")
	@ApiOperation("NOT IMPLEMENTED YET")
	public String executeJdbc(Claim claim) {
		return serviceJdbc.execute(claim);
	}

	@PostMapping("/jpa")
	@ApiOperation("NOT IMPLEMENTED YET")
	public String executeJpa(Claim claim) {
		return serviceJpa.execute(claim);
	}

	@PostMapping("/spring-oracle")
	public OSinAccOutS executeSpringOracle(OSinAccInS pOSinAccInS) {
		return serviceSpringOracle.pProcesaPeticion(pOSinAccInS);
	}

}
