package org.lab.samples.oracle.rest.claim;

import org.lab.samples.oracle.service.claim.oracle.ClaimOracleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/claims/oracle")
public class ClaimOracleController extends AbstractClaimController {

	public ClaimOracleController(ClaimOracleService service) {
		super(service);
	}

}
