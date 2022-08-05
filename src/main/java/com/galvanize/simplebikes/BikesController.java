package com.galvanize.simplebikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BikesController {

    BikesService bikesService;

    public BikesController(BikesService bikesService){
        this.bikesService = bikesService;
    }

    @GetMapping("/api/bikes")
    public BikesList getBikes(){
        return bikesService.getBikes();
    }

}
