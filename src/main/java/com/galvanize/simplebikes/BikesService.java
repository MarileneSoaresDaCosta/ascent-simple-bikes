package com.galvanize.simplebikes;

import org.springframework.stereotype.Service;

@Service
public class BikesService {
    BikesRepository bikesRepository;

    public BikesService(BikesRepository bikesRepository) {
        this.bikesRepository = bikesRepository;
    }

    public BikesList getBikes(){
        return new BikesList(bikesRepository.findAll());
    }

    public BikesList getBikes(String model) {
        return null;
    }

    public Bike addBike(Bike bike){
        return null;
    }
}
