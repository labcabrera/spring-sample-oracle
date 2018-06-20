package org.lab.samples.oracle.model.claim;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_EMAIL_S")
@Getter
@Setter
public class OEmailS {

	private String correo;
	private String codCorresponde;
	private String observaciones;

}