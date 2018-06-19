package org.lab.samples.oracle.service.claim;

import org.lab.samples.oracle.model.participant.claim.OSinAccInS;
import org.lab.samples.oracle.model.participant.claim.OSinAccOutS;

public interface ClaimService {

	OSinAccOutS execute(OSinAccInS claim);

}
