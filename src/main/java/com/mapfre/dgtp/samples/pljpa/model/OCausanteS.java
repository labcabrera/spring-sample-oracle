package com.mapfre.dgtp.samples.pljpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * File generated by the Plugin MAPFRE iPL2WS of GAIA - Ver. 3.1.0 - 05/06/2017
 */
@Data
public class OCausanteS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * nombre Property
	 */
	private String nombre;

	/**
	 * apellidos Property
	 */
	private String apellidos;

	/**
	 * direccion Property
	 */
	private String direccion;

	/**
	 * localidad Property
	 */
	private String localidad;

	/**
	 * provincia Property
	 */
	private String provincia;

	/**
	 * codpostal Property
	 */
	private String codPostal;

	/**
	 * dni Property
	 */
	private String dni;

	/**
	 * numtel1 Property
	 */
	private String numTel1;

	/**
	 * obstel1 Property
	 */
	private String obsTel1;

	/**
	 * numtel2 Property
	 */
	private String numTel2;

	/**
	 * obstel2 Property
	 */
	private String obsTel2;

	/**
	 * entbancaria Property
	 */
	private String entBancaria;

	/**
	 * sucbancaria Property
	 */
	private String sucBancaria;

	/**
	 * dcbanco Property
	 */
	private String dcBanco;

	/**
	 * ctabancaria Property
	 */
	private String ctaBancaria;

	/**
	 * codcia Property
	 */
	private String codCia;

	/**
	 * obsgeneral Property
	 */
	private BigDecimal obsGeneral;

}