package com.mapfre.dgtp.samples.pljpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.SiniestroAcc;
import com.mapfre.dgtp.samples.pljpa.service.AccidentServiceJdbc;

@RestController
@RequestMapping("/api/jdbc/execute")
public class AccidentControllerJdbc {

	@Autowired
	private AccidentServiceJdbc service;

	@PostMapping
	public String execute(SiniestroAcc siniestro) {
		return service.execute(siniestro);
	}

}
