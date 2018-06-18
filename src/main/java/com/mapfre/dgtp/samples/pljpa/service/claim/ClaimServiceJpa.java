package com.mapfre.dgtp.samples.pljpa.service.claim;

import org.springframework.stereotype.Service;

import com.mapfre.dgtp.samples.pljpa.model.Claim;
import com.mapfre.dgtp.samples.pljpa.service.ClaimService;

@Service
public class ClaimServiceJpa implements ClaimService {

	@Override
	public String execute(Claim siniestro) {
		return "TODO";
	}

}
