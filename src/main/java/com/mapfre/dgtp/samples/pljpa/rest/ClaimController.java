package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.Claim;
import com.mapfre.dgtp.samples.pljpa.service.impl.ClaimServiceJdbc;
import com.mapfre.dgtp.samples.pljpa.service.impl.ClaimServiceJpa;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	@Autowired
	private ClaimServiceJdbc serviceJdbc;

	@Autowired
	private ClaimServiceJpa serviceJpa;

	@PostMapping("/jdbc")
	public String executeJdbc(Claim claim) {
		return serviceJdbc.execute(claim);
	}

	@PostMapping("/jpa")
	public String executeJpa(Claim claim) {
		return serviceJpa.execute(claim);
	}

}
