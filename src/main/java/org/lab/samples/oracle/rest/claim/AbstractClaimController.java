package org.lab.samples.oracle.rest.claim;

import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.web.bind.annotation.PostMapping;

public class AbstractClaimController {

	private final ClaimService service;

	public AbstractClaimController(ClaimService service) {
		this.service = service;
	}

	@PostMapping
	public OSinAccOutS execute(OSinAccInS claim) {
		return service.execute(claim);
	}

}
