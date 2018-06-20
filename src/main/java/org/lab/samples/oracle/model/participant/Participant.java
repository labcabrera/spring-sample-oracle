package org.lab.samples.oracle.model.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.lab.samples.oracle.annotation.OracleField;

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
 */
@Entity
@Table(name = "MPD_GEN_PARTICIPANTE")
@Getter
@Setter
public class Participant {

	public static final String ORACLE_TYPE_NAME = "O_AMD_GNL_PAR_S";
	public static final String ORACLE_LIST_NAME = "O_AMD_GNL_PAR_ST";

	@Id
	@Column(name = "COD_PART_PROC")
	@OracleField("par_prc_prc_val")
	private Long id;

	@Column(name = "NOM_PART_PROC")
	@OracleField("nam_par_prc")
	private String name;

	@Column(name = "DES_PART_PROC")
	@OracleField("dsp_par_prc")
	private String description;
}
