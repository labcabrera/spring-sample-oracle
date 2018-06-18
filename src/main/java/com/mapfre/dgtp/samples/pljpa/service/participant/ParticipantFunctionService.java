package com.mapfre.dgtp.samples.pljpa.service.participant;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.mapfre.dgtp.samples.pljpa.StructDefinitionService;
import com.mapfre.dgtp.samples.pljpa.model.Participant;

@Repository
public class ParticipantFunctionService {

	private final ParticipantReadFunction readFunction;

	public ParticipantFunctionService(DataSource dataSource, StructDefinitionService sds) {
		this.readFunction = new ParticipantReadFunction(dataSource, sds);
	}

	public Object find(Participant beanQuery) {
		return readFunction.find(beanQuery);
	}

}
