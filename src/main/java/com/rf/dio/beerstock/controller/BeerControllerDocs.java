package com.rf.dio.beerstock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Manages beer stock")
public interface BeerControllerDocs {


	@ApiOperation(value = "Hello word!!")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Returns a simple hello world")
	})
    String helloAPI();
}
