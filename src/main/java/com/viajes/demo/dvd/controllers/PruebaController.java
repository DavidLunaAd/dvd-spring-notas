package com.viajes.demo.dvd.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
	
	@RequestMapping(value = "prueba")
	public String Prueba() {
		return "prueba";
	}

}
