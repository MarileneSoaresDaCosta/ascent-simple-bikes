package com.galvanize.simplebikes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
//@TestPropertySource("classpath:application-test.properties")
class SimpleBikesApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;


    @Autowired
    BikesRepository bikesRepository;

    Random r = new Random();
    List<Bike> testBikes;

    @BeforeEach
    void setUp(){
        this.testBikes = new ArrayList<>();
        Bike bike;
        String[] colors = {"RED", "BLUE", "GREEN", "ORANGE", "YELLOW", "BLACK", "BROWN",
                "ROOT BEER", "MAGENTA", "AMBER"};
        String[] models = {"Trek", "Schwinn", "Ross", "Wing", "Ostiguy"};

        for (int i = 0; i < 50; i++) {
            String color = colors[r.nextInt(10)];
            String model = models[r.nextInt(5)];
            bike = new Bike(model, 42, color);
            this.testBikes.add(bike);
        }
        bikesRepository.saveAll(this.testBikes);
    }

    @AfterEach
    void tearDown() {
        bikesRepository.deleteAll();
    }



    @Test
	void contextLoads() {
	}

    // get all bikes

    @Test
    void getBikes_exists_returnsBikesList() {
        ResponseEntity<BikesList> response= restTemplate.getForEntity("/api/bikes", BikesList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();

        for(Bike bike : response.getBody().getBikes()){
            System.out.println(bike);
        }

    }

    // search for bike model

    // add bike


    @Test
    void addBike_valid_returnsNewBike() {
        Bike bike  = new Bike("Wing", 40, "RED");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Bike> request = new HttpEntity<>(bike, headers);

        // Act
        ResponseEntity<Bike> response = restTemplate.postForEntity("/api/bikes", request, Bike.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getModel()).isEqualTo(bike.getModel());
    }

}
