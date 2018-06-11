package com.mapfre.dgtp.samples.pljpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import lombok.Data;

@Entity
@Data
@NamedNativeQuery(name = "fn_post_and_comments",
	query = "{ ? = call fn_post_and_comments( ? ) }",
	resultSetMapping = "post_and_comments")
@SqlResultSetMapping(name = "post_and_comments",
	entities = {
		@EntityResult(entityClass = SiniestroAcc.class,
			fields = { @FieldResult(name = "id", column = "p.id"), @FieldResult(name = "title", column = "p.title"),
				@FieldResult(name = "version", column = "p.version"), }),
		@EntityResult(entityClass = SiniestroAcc.class,
			fields = { @FieldResult(name = "id", column = "c.id"), @FieldResult(name = "post", column = "c.post_id"),
				@FieldResult(name = "version", column = "c.version"),
				@FieldResult(name = "review", column = "c.review"), }) })
public class SiniestroAcc {

	@Id
	@Column(name = "NUM_POLIZA", length = 13)
	private String numPoliza;

	@Column(name = "COD_MEDIO", length = 1)
	private String codMedio;

}
