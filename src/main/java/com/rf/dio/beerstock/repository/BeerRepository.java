package com.rf.dio.beerstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.dio.beerstock.entity.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long>{
	
	Optional<Beer> findByName(String name);

}
