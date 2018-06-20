package org.lab.samples.oracle.repositories;

import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <code>JpaRepository</code> for {@link Participant} entity.
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
