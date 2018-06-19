package org.lab.samples.oracle.rest.claim;

import org.lab.samples.oracle.model.participant.claim.OSinAccInS;
import org.lab.samples.oracle.model.participant.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.web.bind.annotation.PostMapping;

public class AbstractClaimController {

	private final ClaimService service;

	public AbstractClaimController(ClaimService service) {
		this.service = service;
	}

	@PostMapping
	public OSinAccOutS executeSpringOracle(OSinAccInS claim) {
		return service.execute(claim);
	}

}
