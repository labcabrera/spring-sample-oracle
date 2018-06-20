package org.lab.samples.oracle.service.claim;

import org.lab.samples.oracle.model.claim.OSinAccInS;
import org.lab.samples.oracle.model.claim.OSinAccOutS;

public interface ClaimService {

	OSinAccOutS execute(OSinAccInS claim);

}
