package org.lab.samples.oracle.model.claim;

import java.util.Date;
import java.util.List;

import org.lab.samples.oracle.annotation.OracleCollection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OSinAccInS {

	private String NUM_POLIZA;
	private String codMedio;
	private String codGarant;
	private String codPrioridad;
	private Date FEC_OCURRENCIA;
	private String horaOcurrencia;
	private String lugarOcurrencia;
	private String localidad;
	private String codPostal;
	private String ine;
	private String codCausa;
	private String tipoExpediente;
	private String claseExpdte;
	private String nuuma;

	private OCorresExpdteS correspondencia;

	private OComunicanteS comunicante;

	private OFederacionS federacion;

	private OColegioS colegio;

	private OAccidenteS accidente;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> descripcion;

}