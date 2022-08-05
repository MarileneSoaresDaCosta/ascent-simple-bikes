package com.galvanize.simplebikes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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

    ObjectMapper mapper = new ObjectMapper();

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
    @Test
    void addBike_valid_returnsNewBike() throws Exception {
        // Arrange
        Bike bike = new Bike("Ross", 22, "Red");
        when(bikesService.addBike(any(Bike.class))).thenReturn(bike);
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bikes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bike)))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("model").value("Ross"));
    }

    // GET search /api/bikes/{model}
    @Test
    void getBikes_withParam_returnsMatchingBikes() throws Exception {
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
        when(bikesService.getBikes(anyString())).thenReturn(new BikesList(bikes));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes/Trek" ))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bikes", hasSize(5)));
    }

}
