package com.rf.dio.beerstock.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rf.dio.beerstock.dto.BeerDTO;
import com.rf.dio.beerstock.entity.Beer;
import com.rf.dio.beerstock.exception.BeerAlreadyRegisteredException;
import com.rf.dio.beerstock.exception.BeerNotFoundException;
import com.rf.dio.beerstock.mapper.BeerMapper;
import com.rf.dio.beerstock.repository.BeerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

	private final BeerRepository beerRepository;

	private BeerMapper beerMapper = BeerMapper.INSTANCE;// CONT RESOLVENDO ESSE ERRRO

	public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
		verifyIfIsAlreadyRegistered(beerDTO);
		Beer beer = beerMapper.toModel(beerDTO);
		Beer savedBeer = beerRepository.save(beer);
		return beerMapper.toDTO(savedBeer);
	}

	public BeerDTO findByName(String name) throws BeerNotFoundException {
		Beer foundBeer = beerRepository.findByName(name).orElseThrow(() -> new BeerNotFoundException(name));
		return beerMapper.toDTO(foundBeer);
	}

	private void verifyIfIsAlreadyRegistered(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
		Optional<Beer> optSavedBeer = beerRepository.findByName(beerDTO.getName());
		if (optSavedBeer.isPresent()) {
			throw new BeerAlreadyRegisteredException(beerDTO.getName());
		}

	}

	public List<BeerDTO> listAll() {
		return beerRepository.findAll().stream().map(beerMapper::toDTO).collect(Collectors.toList());
	}

}
