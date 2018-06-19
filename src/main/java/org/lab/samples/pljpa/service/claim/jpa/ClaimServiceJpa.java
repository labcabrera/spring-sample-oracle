package org.lab.samples.pljpa.service.claim.jpa;

import org.lab.samples.pljpa.model.Claim;
import org.lab.samples.pljpa.service.claim.ClaimService;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceJpa implements ClaimService {

	@Override
	public String execute(Claim siniestro) {
		return "TODO";
	}

}
