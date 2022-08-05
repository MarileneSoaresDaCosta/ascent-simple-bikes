package com.galvanize.simplebikes;

import java.util.ArrayList;
import java.util.List;

public class BikesList {
    private List<Bike> bikes = new ArrayList<>();

    public BikesList(){

    }

    public BikesList(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public boolean isEmpty(){
        return bikes.isEmpty();
    }

    @Override
    public String toString() {
        return "BikesList{" +
                "bikes=" + bikes +
                '}';
    }
}
