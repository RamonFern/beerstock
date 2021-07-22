package com.rf.dio.beerstock.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.rf.dio.beerstock.builder.BeerDTOBuilder;
import com.rf.dio.beerstock.dto.BeerDTO;
import com.rf.dio.beerstock.entity.Beer;
import com.rf.dio.beerstock.exception.BeerAlreadyRegisteredException;
import com.rf.dio.beerstock.exception.BeerNotFoundException;
import com.rf.dio.beerstock.mapper.BeerMapper;
import com.rf.dio.beerstock.repository.BeerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {
	
	@Mock
    private BeerRepository beerRepository;

	@Autowired
    private BeerMapper beerMapper= BeerMapper.INSTANCE;
    
    @InjectMocks
    private BeerService beerService;
    
    @Test
    void whenNewBeerInformedThenShouldBeCreated() throws BeerAlreadyRegisteredException {
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSavedBeer = beerMapper.toModel(beerDTO);

        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertEquals(beerDTO.getId(), createdBeerDTO.getId());
        assertEquals(beerDTO.getName(), createdBeerDTO.getName());
        assertEquals(beerDTO.getType(), createdBeerDTO.getType());
    }

    
    @Test
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer duplicatedBeer = beerMapper.toModel(beerDTO);

        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        assertThrows(BeerAlreadyRegisteredException.class,() -> beerService.createBeer(beerDTO)) ;
    }
    
    @Test
    void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(expectedBeerDTO);

        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(expectedFoundBeer));

        BeerDTO foundBeerDTO = beerService.findByName(expectedBeerDTO.getName());

        assertEquals(expectedBeerDTO, foundBeerDTO);
    }

    @Test
    void whenNotRegisteredBeerNameIsGivenThenThrowAnException() {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());

        assertThrows(BeerNotFoundException.class,() -> beerService.findByName(expectedBeerDTO.getName()));
    }
    
    @Test
    void whenListBeerIsCalledThenReturnAListOfBeers() {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(expectedBeerDTO);

        when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));

        List<BeerDTO> foundBeerDTO = beerService.listAll();

        assertFalse(foundBeerDTO.isEmpty());
        assertEquals(expectedBeerDTO, foundBeerDTO.get(0));
    }

    @Test
    void whenListBeerIsCalledThenReturnAnEmptyList() {
        when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<BeerDTO> foundBeerDTO = beerService.listAll();

        assertTrue(foundBeerDTO.isEmpty());
    }

}
