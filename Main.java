package org.example;

import org.example.objects.Vehicle;
import org.example.objects.Car;
import org.example.objects.Motorbike;

import java.security.PublicKey;
import java.util.ArrayList;
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
        System.out.println("Choose an option");
        
        while(onConsole) {
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
                System.out.println("The vehicle type" + type + "is not available for registration. Please select other.");
                return false;
        }
    }

    public static void commonUserInputGuide() {
        System.out.println("Specify the vehicles features by comma-separated values like:\n ");
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
            if (vehicleType.equals("car")) {
                addCar(features);
            } else {
                addMotorbike(features);
            }
        } catch (Exception e) {
            System.out.println("Error at adding a vehicle: " + e.toString());
        }
    }

    public static void addCar(String[] features) {
        vehicles.add(new Car(
                features[0],
                features[1],
                features[2],
                features[3],
                Integer.parseInt(features[4].trim()),
                Boolean.parseBoolean(features[5].trim())
                )
        );
        System.out.println("Vehicle " + features[0] + "added" + vehicles.size());
    }
    public static void addMotorbike(String[] features) {
        vehicles.add(new Motorbike(
                features[0],
                features[1],
                features[2].trim(),
                features[3],
                Integer.parseInt(features[4].trim()),
                Boolean.parseBoolean(features[5].trim())
                )
        );
    }
}