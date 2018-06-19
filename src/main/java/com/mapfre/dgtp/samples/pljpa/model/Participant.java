package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad participante.
 * 
 * NOTA: para que funcione correctamente la conversion de tipos de Oracle (STRUTS) los nombres de los campos deben
 * coincidir con los nombres definidos en Oracle. De momento no se ha implementado otro modo para poder resolver dichos
 * mapeos aunque seria sencillo realizarlo a traves de anotaciones de un modo similar a JPA.
 * 
 * NOTA: hay que tener en cuenta que por un lado se estan mapeando los nombres de la tabla y por otro lado los nombres
 * del objeto de Oracle de intercambio, que no coinciden. Por ello las anotaciones de JPA y los nombres no coinciden.
 * 
 * @author Arquitectura
 */
@Entity
@Table(name = "MPD_GEN_PARTICIPANTE")
@Getter
@Setter
public class Participant {

	@Id
	@Column(name = "COD_PART_PROC")
	private Long par_prc_prc_val;

	@Column(name = "NOM_PART_PROC")
	private String nam_par_prc;

	@Column(name = "DES_PART_PROC")
	private String dsp_par_prc;
}
