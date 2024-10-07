package org.example.objects;

public class Car extends Vehicle{
    Integer numberOfDoors;
    Boolean convertible;
    Boolean driverSeatbealtFasten;

    public Car(String brand, String model, String year, String licensePlate, Integer numberOfDoors, boolean convertible) {
        super(brand, model, year, licensePlate);
        this.numberOfDoors = numberOfDoors;
        this.convertible = convertible;
    }

    public Boolean getDriverSeatbealtFasten() {
        return driverSeatbealtFasten;
    }

    public void setDriverSeatbealtFasten(Boolean driverSeatbealtFasten) {
        this.driverSeatbealtFasten = driverSeatbealtFasten;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public void horn() {
        System.out.println("The car's horn roars loudly.");
    }

    @Override
    public void startEngine() {
        if (!driverSeatbealtFasten) {
            System.out.println("The driver must fasten its seatbealt before starting the engine.");
            return;
        }

        if (started) {
            System.out.println("The bike's engine has already being started.");
        } else {
            started = true;
            System.out.println("The bike's engine was started.");
        }
    }
}
