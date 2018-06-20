package org.lab.samples.oracle.model.claim;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * File generated by the Plugin MAPFRE iPL2WS of GAIA - Ver. 3.1.0 - 05/06/2017
 */
@Data
public class OSinAccInS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * numpoliza Property
	 */
	private String NUM_POLIZA;

	/**
	 * codmedio Property
	 */
	private String codMedio;

	/**
	 * codgarant Property
	 */
	private String codGarant;

	/**
	 * codprioridad Property
	 */
	private String codPrioridad;

	/**
	 * fecocurrencia Property
	 */
	private Date FEC_OCURRENCIA;

	/**
	 * horaocurrencia Property
	 */
	private String horaOcurrencia;

	/**
	 * lugarocurrencia Property
	 */
	private String lugarOcurrencia;

	/**
	 * localidad Property
	 */
	private String localidad;

	/**
	 * codpostal Property
	 */
	private String codPostal;

	/**
	 * ine Property
	 */
	private String ine;

	/**
	 * codcausa Property
	 */
	private String codCausa;

	/**
	 * tipoexpediente Property
	 */
	private String tipoExpediente;

	/**
	 * claseexpdte Property
	 */
	private String claseExpdte;

	/**
	 * nuuma Property
	 */
	private String nuuma;

	/**
	 * descripcion Property
	 */
	private List<ODescripcionS> descripcion;

	/**
	 * correspondencia Property
	 */
	private OCorresExpdteS correspondencia;

	/**
	 * comunicante Property
	 */
	private OComunicanteS comunicante;

	/**
	 * federacion Property
	 */
	private OFederacionS federacion;

	/**
	 * colegio Property
	 */
	private OColegioS colegio;

	/**
	 * accidente Property
	 */
	private OAccidenteS accidente;

}