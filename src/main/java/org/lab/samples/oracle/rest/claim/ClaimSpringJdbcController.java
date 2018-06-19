package org.lab.samples.oracle.rest.claim;

import org.lab.samples.oracle.service.claim.spring.ClaimSpringJdbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/claims/spring-jdbc")
public class ClaimSpringJdbcController extends AbstractClaimController {

	public ClaimSpringJdbcController(ClaimSpringJdbcService service) {
		super(service);
	}

}
