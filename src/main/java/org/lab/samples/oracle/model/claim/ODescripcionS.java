package org.lab.samples.oracle.model.claim;

import org.lab.samples.oracle.annotation.OracleStruct;

import lombok.Getter;
import lombok.Setter;

@OracleStruct("O_DESCRIPCION_S")
@Getter
@Setter
public class ODescripcionS {

	private String descripcion;

}