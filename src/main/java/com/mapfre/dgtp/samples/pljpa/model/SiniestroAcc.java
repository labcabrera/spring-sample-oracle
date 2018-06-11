package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SiniestroAcc {

	@Id
	@Column(name = "NUM_POLIZA", length = 13)
	private String numPoliza;

	@Column(name = "COD_MEDIO", length = 1)
	private String codMedio;

}
