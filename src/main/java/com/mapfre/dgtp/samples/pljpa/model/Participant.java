package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MPD_GEN_PARTICIPANTE")
//@formatter:off
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = Participant.NamedQueries.FIND,
		procedureName = "MPD_LD.dl_gnl_par.f_get",
		resultClasses = { Participant.class },
		parameters = {
			@StoredProcedureParameter(name = "p_o_amd_gnl_par_s", type = Participant.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "O_AMD_GNL_PAR_ST", type = Participant.class, mode = ParameterMode.OUT) }),
	})
//@formatter:on
@Getter
@Setter
public class Participant {

	public interface NamedQueries {
		String FIND = "participantFind";
	}

	@Id
	@Column(name = "COD_PART_PROC")
	private Long id;

	@Column(name = "NOM_PART_PROC")
	private String name;

	@Column(name = "DES_PART_PROC")
	private String desc;
}
