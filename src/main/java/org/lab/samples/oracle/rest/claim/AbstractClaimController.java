package org.lab.samples.oracle.rest.claim;

import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;
import org.lab.samples.oracle.service.claim.ClaimService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractClaimController {

	private final ClaimService service;

	public AbstractClaimController(ClaimService service) {
		this.service = service;
	}

	@PostMapping
	public OSinAccOutS execute(@RequestBody OSinAccInS claim) {
		try {
			return service.execute(claim);
		}
		catch (RuntimeException ex) {
			log.error("Claim process error", ex);
			throw ex;

		}
	}

}
