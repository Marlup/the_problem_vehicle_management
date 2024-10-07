package org.example.objects;

public class Vehicle {
    String brand;
    String model;
    String year;
    String licensePlate;
    boolean started;

    public Vehicle(String brand, String model, String year, String licensePlate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isStarted() {
        return started;
    }

    public void startEngine() {
        if (!started) {
            started = true;
            System.out.println("The vehicle's engine was started.");
        } else {
            System.out.println("The vehicle's engine has already being started.");
        }
    }

    public void accelerate() {
        if (started) {
            System.out.println("The vehicle accelerates.");
        } else {
            System.out.println("The vehicle's engine has not started. Start it first.");
        }
    }

    public void slowDown() {
        System.out.println("The vehicle slows down.");
    }
}