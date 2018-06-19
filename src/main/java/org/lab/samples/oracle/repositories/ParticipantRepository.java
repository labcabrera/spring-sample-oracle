package org.lab.samples.oracle.repositories;

import org.lab.samples.oracle.model.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
