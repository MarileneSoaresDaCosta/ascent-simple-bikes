package com.galvanize.simplebikes;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/bikes")
    public Bike addBike(@RequestBody Bike bike) {
        return bikesService.addBike(bike);
    }

    @GetMapping("/api/bikes/{model}")
    public BikesList getBikes(@PathVariable String model) {
        BikesList bikes = bikesService.getBikes(model);
        return bikes;
    }

}
