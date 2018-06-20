package org.lab.samples.oracle.model.claim;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_CORRES_EXPDTE_S")
@Getter
@Setter
public class OCorresExpdteS {

	private OEmailS email;
	private String tipoVia;
	private String nomVia;
	private String numero;
	private String portal;
	private String esc;
	private String piso;
	private String puerta;
	private String localidad;
	private String codPostal;
	private String codPais;
	private String apartado;
	private String nomDest;

}