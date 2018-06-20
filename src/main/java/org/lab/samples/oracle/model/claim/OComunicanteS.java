package org.lab.samples.oracle.model.claim;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_COMUNICANTE_S")
@Getter
@Setter
public class OComunicanteS {

	private String nombre;
	private String apellidos;
	private String telefono;
	private String codRelacion;

}