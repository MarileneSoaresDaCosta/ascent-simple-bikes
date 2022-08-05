package com.galvanize.simplebikes;

import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Bike> bikes = bikesRepository.findByModelContains(model);
        if(!bikes.isEmpty()){
            return new BikesList(bikes);
        }
        return null;
    }

    public Bike addBike(Bike bike){
        return bikesRepository.save(bike);
    }
}
