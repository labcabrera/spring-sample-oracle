package org.lab.samples.oracle.model.claim;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_FEDERACION_S")
@Getter
@Setter
public class OFederacionS {

	private String nombre;
	private String apellidos;
	private String domicilio;
	private String localidad;
	private String codPostal;
	private String dni;
	private String telef1;
	private String telef2;
	private String licencia;
	private String club;
	private String tclub;
	private String telClub;
	private String atendido;
	private String cliAtendido;
	private String cliRecomendada;

}