package com.carrentmanagement;

public class Car {
    private int id;
    private String model;
    private String brand;
    private boolean availability;

    // Constructor
    public Car(int id, String model, String brand, boolean availability) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.availability = availability;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", availability=" + availability +
                '}';
    }
}
