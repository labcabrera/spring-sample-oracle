package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MPD_GEN_PARTICIPANTE")
public class Participant {

	@Id
	@Column(name = "COD_PART_PROC")
	private Long id;

	@Column(name = "NOM_PART_PROC")
	private String name;

	@Column(name = "DES_PART_PROC")
	private String desc;
}
