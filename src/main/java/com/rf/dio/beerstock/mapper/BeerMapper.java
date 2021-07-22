package com.rf.dio.beerstock.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rf.dio.beerstock.dto.BeerDTO;
import com.rf.dio.beerstock.entity.Beer;

@Mapper
public interface BeerMapper {

	BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

	Beer toModel(BeerDTO beerDTO);

	BeerDTO toDTO(Beer beer);
}
