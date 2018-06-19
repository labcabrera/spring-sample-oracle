package org.lab.samples.oracle.service.claim.jpa;

import org.lab.samples.oracle.model.Claim;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceJpa implements ClaimService {

	@Override
	public String execute(Claim siniestro) {
		return "TODO";
	}

}
