package com.rf.dio.beerstock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rf.dio.beerstock.dto.BeerDTO;
import com.rf.dio.beerstock.exception.BeerAlreadyRegisteredException;
import com.rf.dio.beerstock.exception.BeerNotFoundException;
import com.rf.dio.beerstock.service.BeerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

	private final BeerService beerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
		return beerService.createBeer(beerDTO);
	}

	@GetMapping("/{name}")
	public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
		return beerService.findByName(name);
	}

	@GetMapping
	public List<BeerDTO> listBeers() {
		return beerService.listAll();
	}

}
