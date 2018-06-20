package org.lab.samples.oracle.model.claim;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OCausanteS {

	private String nombre;
	private String apellidos;
	private String direccion;
	private String localidad;
	private String provincia;
	private String codPostal;
	private String dni;
	private String numTel1;
	private String obsTel1;
	private String numTel2;
	private String obsTel2;
	private String entBancaria;
	private String sucBancaria;
	private String dcBanco;
	private String ctaBancaria;
	private String codCia;
	private BigDecimal obsGeneral;

}