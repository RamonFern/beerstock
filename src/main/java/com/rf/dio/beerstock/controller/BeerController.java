package com.rf.dio.beerstock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController implements BeerControllerDocs{

	@Override
	public String helloAPI() { ///decobrir qula a url que mostra o projeto executando
		 return "Hello API!!";
	}

}
