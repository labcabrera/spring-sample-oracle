package com.mapfre.dgtp.samples.pljpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapfre.dgtp.samples.pljpa.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
