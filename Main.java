package org.example;

import org.example.objects.Vehicle;
import org.example.objects.Car;
import org.example.objects.Motorbike;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        boolean onConsole = true;
        String selection;
        String vehicleFeatures;
        String vehicleType;
        Scanner userInput = new Scanner(System.in);

        // Starts the console

        while(onConsole) {
            System.out.println("Please, choose an option number:");
            System.out.println("1. Add vehicle.");
            System.out.println("2. Show vehicles.");
            System.out.println("3. Exit.");

            selection = userInput.nextLine();

            switch(selection) {
                case "1":
                    // Capture vehicle type by the next terminal input
                    System.out.println("Please specify the vehicle type:");
                    vehicleType = userInput.nextLine();

                    // Validate the vehicle type
                    if(!validateVehicle(vehicleType))
                        continue;

                    // Guide message after validation


                    // Capture vehicle features by the next terminal input
                    vehicleFeatures = userInput.nextLine();

                    // Add the vehicle
                    addVehicle(vehicleFeatures);
                    break;
                case "2":
                    showVehicles();
                    break;
                case "3":
                    onConsole = false;
                    System.out.println("Closing the console. Thank you.");
                    break;
                default:
                    System.out.println("Unknown option. Please, specify one the available options below.");
                    break;
            }
        }
        userInput.close();
    }

    public static boolean validateVehicle(String type) {
        switch(type) {
            case "car":
                commonUserInputGuide();
                System.out.println("\t5) the number of doors as an integer\n\t6) whether the car is convertible.");
                return true;
            case "motorbike":
                commonUserInputGuide();
                System.out.println("\t5) the cylinders (as an integer).\n\t6) whether the motorbike has a sidecar.");
                return true;
            default:
                System.out.println("The vehicle type '" + type + "' is not available for registration. Please select one among 'car' or 'motorbike'.");
                return false;
        }
    }

    public static void commonUserInputGuide() {
        System.out.println("Specify the vehicles features by comma-separated values.\n(Example: Renault,Rafale,2024,1111AAA,4,false)\n ");
        System.out.println("\t1) brand.\n\t2) model.\n\t3) year.\n\t4) licensePlate.");
    }

    public static void showVehicles() {
        System.out.println("\nShowing the vehicles stored:");
        if (vehicles.isEmpty()) {
            System.out.println("\tThere are not any stored vehicles.\n");
        } else {
            showVehicles(vehicles);
        }
    }

    public static void showVehicles(List<Vehicle> vehicles) {
        int counter = 0;
        System.out.println("----------");
        for (Vehicle vehicle : vehicles) {
            counter++;

            // Show vehicle counter
            System.out.println("Vehicle # " + counter);

            // Show vehicle attributes
            System.out.println("\tBrand: " + vehicle.getBrand());
            System.out.println("\tModel: " + vehicle.getModel());
            System.out.println("\tYear: " + vehicle.getYear());
            System.out.println("\tLicense Plate: " + vehicle.getLicensePlate());
            //System.out.println("\tNumber of doors:" + car.getNumberOfDoors().toString());
            //System.out.println(car.getConvertible() ? "Convertible" : "No convertible");

            // Skip line
            System.out.println();
        }
        System.out.println("End of the list\n----------");
    }

    public static void addVehicle(String rawFeatures) {
        String []features = rawFeatures.split(",");
        String vehicleType = features[0];

        try {
            int nFeatures = (int) Arrays.stream(features).count();
            if (nFeatures < 6) {
                throw new Exception("You have provided " + nFeatures + " feature, but 6 features are the minimum required.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            if (features[2].trim().length() != 4) {
                throw new Exception("year value must be equal to 4 digits. Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        int vehicleYear = Integer.parseInt(features[2].trim());
        try {
            // Validate the year 2)
            if ((vehicleYear < 1900) || (vehicleYear > 2050)) {
                throw new Exception("year value must be between 1900 and 2050. Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Add a vehicle
        if (vehicleType.equals("car")) {
            addCar(features);
        } else {
            addMotorbike(features);
        }
    }

    public static void addCar(String[] features) {
        Car newCar = new Car(
                features[0],
                features[1],
                features[2],
                features[3],
                Integer.parseInt(features[4].trim()),
                Boolean.parseBoolean(features[5].trim())
        );
        vehicles.add(newCar);
        System.out.println("Vehicle " + features[0] + "added" + vehicles.size());
    }
    public static void addMotorbike(String[] features) {
        Motorbike newMotorbike = new Motorbike(
                features[0],
                features[1],
                features[2].trim(),
                features[3],
                Integer.parseInt(features[4].trim()),
                Boolean.parseBoolean(features[5].trim())
        );
        vehicles.add(newMotorbike);
    }
}