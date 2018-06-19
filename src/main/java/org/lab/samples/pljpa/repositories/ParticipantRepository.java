package org.lab.samples.pljpa.repositories;

import org.lab.samples.pljpa.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
