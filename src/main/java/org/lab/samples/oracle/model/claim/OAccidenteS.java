package org.lab.samples.oracle.model.claim;

import java.util.List;

import org.lab.samples.oracle.annotation.OracleCollection;
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
	private String bajaLabor;
	private String dias;
	private String localizacion;
	private String localiAct;
	private String localPostal;
	private String localTfno;
	private String cliRecomendada;
	private String fallecimiento;
	private String tratamiento;
	private String ingresado;
	private String cliRecomendada2;

	private OCausanteS causante;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> formaOcur;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> danosTerceros;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> polizas;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> clinicas;

	@OracleCollection("O_DESCRIPCION_ST")
	private List<ODescripcionS> lesiones;

}