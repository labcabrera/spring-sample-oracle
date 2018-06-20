package org.lab.samples.oracle.model.claim;

import java.util.List;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_ACCIDENTE_S")
@Getter
@Setter
public class OAccidenteS {

	private String nomDecla;
	private String apelDecla;
	private String tfnoDecla;
	private String relacion;
	private String nomAccidentado;
	private String apelAccidentado;
	private String tfnoAccidentado;
	private String domAccidentado;
	private String locAccidentado;
	private String distAccidentado;
	private String lugarOcur;
	private String horaOcur;
	private List<ODescripcionS> formaOcur;
	private OCausanteS causante;
	private String cliRecomendada;
	private List<ODescripcionS> danosTerceros;
	private List<ODescripcionS> polizas;
	private String fallecimiento;
	private List<ODescripcionS> clinicas;
	private String tratamiento;
	private String ingresado;
	private String cliRecomendada2;
	private List<ODescripcionS> lesiones;
	private String bajaLabor;
	private String dias;
	private String localizacion;
	private String localiAct;
	private String localPostal;
	private String localTfno;

}