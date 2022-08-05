package com.galvanize.simplebikes;

public class Bike {
    private String model;
    private int height;
    private String color;

    public Bike(){
    }
    public Bike(String model, int height, String color) {
        this.model = model;
        this.height = height;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "model='" + model + '\'' +
                ", height=" + height +
                ", color='" + color + '\'' +
                '}';
    }
}
