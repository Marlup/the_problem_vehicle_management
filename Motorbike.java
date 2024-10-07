package org.example.objects;

public class Motorbike extends Vehicle {
    Integer cylinders;
    boolean hasSidecar;
    boolean helmetOn;

    public Motorbike(String brand, String model, String year, String licensePlate, Integer cylinders, boolean hasSidecar) {
        super(brand, model, year, licensePlate);
        this.cylinders = cylinders;
        this.hasSidecar = hasSidecar;
    }

    // Cylinder get-set
    public Integer getCylinders() {
        return cylinders;
    }

    public void setCylinders(Integer cylinders) {
        this.cylinders = cylinders;
    }

    // hasSidecar get-set
    public boolean getHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    // wheelie action
    public void doWheelie() {
        if (started) {
            System.out.println("The driver does a wheelie.");
        } else {
            System.out.println("The driver cannot do a wheelie. The engine must be started first.");
        }
    }

    // putHelmet action
    public void putHelmet() {
        if (helmetOn) {
            System.out.println("The driver has already put its helmet on.");
        } else {
            helmetOn = true;
            System.out.println("The driver has put its helmet on.");
        }
    }

    @Override
    public void startEngine() {
        if (!helmetOn) {
            System.out.println("The driver must put its helmet on before starting the engine.");
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
