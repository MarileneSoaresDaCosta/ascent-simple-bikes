package com.galvanize.simplebikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BikesController.class)
public class BikesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BikesService bikesService;

    // GET get a list of all bikes
    @Test
    void getBikes_NoParams_returnsBikesList() throws Exception {
        // Arrange
        List<Bike> bikes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Bike bike;
            if (i % 2 == 0) {
                bike = new Bike("Trek", 42, "Silver" );
            } else {
                bike = new Bike("Schwinn", 40, "Blue" );
            }
            bikes.add(bike);
        }
        when(bikesService.getBikes()).thenReturn(new BikesList(bikes));

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes"))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bikes", hasSize(5) ));
    }


    // POST a new bike


    // Arrange

    // Act

    // Assert
    // GET search for a bike


    // Arrange

    // Act

    // Assert
}
