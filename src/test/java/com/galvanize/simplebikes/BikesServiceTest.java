package com.galvanize.simplebikes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BikesServiceTest {

    private BikesService bikesService;

    @Mock
    BikesRepository bikesRepository;


    @BeforeEach
    void setUp(){
        bikesService = new BikesService(bikesRepository);
    }

    @Test
    void getBikes_noArgs_returnsList(){
        Bike bike = new Bike("Ross", 22, "Red");
        when(bikesRepository.findAll()).thenReturn(Arrays.asList(bike));
        BikesList bikes = bikesService.getBikes();
        assertThat(bikes).isNotNull();
        assertThat(bikes.isEmpty()).isFalse();

    }

    @Test
    void addBikes_valid_returnsBike(){
        Bike bike = new Bike("Ross", 22, "Red");
        when(bikesRepository.save(any(Bike.class)))
                .thenReturn(bike);
        Bike newBike = bikesService.addBike(bike);
        assertThat(bike).isNotNull();
        assertThat(bike.getModel()).isEqualTo("Ross");

    }

    @Test
    void getBikes_withArg_returnsListWithMatchingBikes(){
        Bike bike = new Bike("Ross", 22, "Red");
        when(bikesRepository.findByModelContains(anyString())).thenReturn(Arrays.asList(bike));
        BikesList bikes = bikesService.getBikes("Ross");
        assertThat(bikes).isNotNull();
        assertThat(bikes.isEmpty()).isFalse();

    }

}
