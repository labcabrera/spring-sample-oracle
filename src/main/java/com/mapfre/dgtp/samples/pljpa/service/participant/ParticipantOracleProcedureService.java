package com.mapfre.dgtp.samples.pljpa.service.participant;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.model.Participant;
import com.mapfre.dgtp.samples.pljpa.oracle.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantInsertProcedure;
import com.mapfre.dgtp.samples.pljpa.service.participant.oracle.ParticipantReadProcedure;

@Repository
public class ParticipantOracleProcedureService {

	private final ParticipantReadProcedure readFunction;
	private final ParticipantInsertProcedure insertProcedure;

	public ParticipantOracleProcedureService(DataSource dataSource, StructDefinitionService definitionService) {
		this.readFunction = new ParticipantReadProcedure(dataSource, definitionService);
		this.insertProcedure = new ParticipantInsertProcedure(dataSource, definitionService);
	}

	public List<Participant> find(Participant beanQuery) {
		return readFunction.find(beanQuery);
	}

	public Participant findById(Long id) {
		Participant queryBean = new Participant();
		queryBean.setPar_prc_prc_val(id);
		List<Participant> list = find(queryBean);
		return list.isEmpty() ? null : list.iterator().next();
	}

	public Participant insert(Participant entity) {
		return insertProcedure.insert(entity, "LBARRA1");
	}
}
