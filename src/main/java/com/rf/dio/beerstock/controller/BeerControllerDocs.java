package com.rf.dio.beerstock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.rf.dio.beerstock.dto.BeerDTO;
import com.rf.dio.beerstock.exception.BeerAlreadyRegisteredException;
import com.rf.dio.beerstock.exception.BeerNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Manages beer stock")
public interface BeerControllerDocs {

	@ApiOperation(value = "Beer creation operation")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success beer creation"),
			@ApiResponse(code = 400, message = "Missing required fields or wrong field range value.") })
	BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException;

	@ApiOperation(value = "Returns beer found by a given name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success beer found in the system"),
			@ApiResponse(code = 404, message = "Beer with a given name not found.") })
	BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException;

	@ApiOperation(value = "Returns a list of all beers registered in the system")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of all beers registered in the system"), })
	List<BeerDTO> listBeers();
	
	
}
