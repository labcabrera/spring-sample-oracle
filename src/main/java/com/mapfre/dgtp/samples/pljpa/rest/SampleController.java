package com.mapfre.dgtp.samples.pljpa.rest;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapfre.dgtp.samples.pljpa.model.SiniestroAcc;

@RestController
@RequestMapping("/api/test")
public class SampleController {

	@Autowired
	private AccidentService service;

	@GetMapping
	public String test() {
		SiniestroAcc siniestro = new SiniestroAcc();
		try {
			return service.execute(siniestro);
		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
