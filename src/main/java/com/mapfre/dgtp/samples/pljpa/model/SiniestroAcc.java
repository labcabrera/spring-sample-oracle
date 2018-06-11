package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class SiniestroAcc {

	@Column(name = "NUM_POLIZA", length = 13)
	private String numPoliza;

}
