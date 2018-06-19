package org.lab.samples.oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import lombok.Data;

/**
 * Representa un siniestro.
 */
@Entity
@Data
//@formatter:off
@NamedNativeQuery(
	name = "claim-insert",
	query = "{ ? = call PL MPG_K_EX_SINIESTRO_ACCIDENTE.PR_PROCESA_PETICION(?,?,?,?) }",
	resultSetMapping = "claim-result-set-mapping")
@SqlResultSetMapping(
	name = "claim-result-set-mapping",
	entities = {
		@EntityResult(
			entityClass = Claim.class,
			fields = {
				@FieldResult(name = "field1", column = "p.someField1"),
				@FieldResult(name = "field2", column = "p.someField2"),
				@FieldResult(name = "field3", column = "p.someField3"), }),
		@EntityResult(
			entityClass = Claim.class,
			fields = {
				@FieldResult(name = "field1", column = "c.someField1"),
				@FieldResult(name = "field2", column = "c.someField2"),
				@FieldResult(name = "field3", column = "c.someField3"),
				@FieldResult(name = "field4", column = "c.someField4"), }) })
//@formatter:on
public class Claim {

	@Id
	@Column(name = "NUM_POLIZA", length = 13)
	private String NUM_POLIZA;

	@Column(name = "COD_MEDIO", length = 1)
	private String COD_MEDIO;

}
