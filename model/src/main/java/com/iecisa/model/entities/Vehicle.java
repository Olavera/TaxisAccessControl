package com.iecisa.model.entities;

/**
 * @author darevalo
 */
public class Vehicle {

    private String numberPlate;
    private String make;
    private String model;
    private String color;

    public Vehicle() {
    }

    public Vehicle(String numberPlate, String make, String model, String color) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
        this.color = color;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
