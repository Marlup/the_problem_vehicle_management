package org.example;

import org.example.objects.Vehicle;
import org.example.objects.Car;
import org.example.objects.Motorbike;

import java.util.*;

public class Main {
    // Constants
    private static final List<String> POSITIVE_MESSAGE_OPTIONS = Arrays.asList("y", "yes", "true");
    private static final List<String> NEGATIVE_MESSAGE_OPTIONS = Arrays.asList("n", "no", "false");

    // Containers for vehicles
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
                    if(validateVehicle(vehicleType)) {
                        // Capture vehicle features by the next terminal input
                        vehicleFeatures = userInput.nextLine();

                        // Add the vehicle
                        addVehicle(vehicleFeatures, vehicleType);
                    }

                    break;
                case "2":
                    showVehicles();
                    break;
                case "3":
                    onConsole = false;
                    System.out.println("Closing the console. Thank you.");
                    break;
                default:
                    System.out.println("Unknown option. Please, specify one of the available options below.");
                    break;
            }
        }
        userInput.close();
    }

    public static boolean validateVehicle(String vehicleType) {
        switch(vehicleType) {
            case "car":
                commonUserInputGuide(vehicleType);
                System.out.println("\t5) the number of doors as an integer (from 3 to 7)." +
                        "\n\t6) whether the car is convertible (yes/y or no/n).");
                return true;
            case "motorbike":
                commonUserInputGuide(vehicleType);
                System.out.println("\t5) the cylinders (from 1 to 12)." +
                        "\n\t6) whether the motorbike has a sidecar (yes/y or no/n).");
                return true;
            default:
                System.out.println("The vehicle type '" + vehicleType + "' is not available for registration. " +
                        "Please select one among 'car' or 'motorbike'.");
                return false;
        }
    }

    public static void commonUserInputGuide(String vehicleType) {
        System.out.println(String.format("Specify the %s vehicle's features by comma-separated values.\n\n"
                + "(Example: Renault,Rafale,2024,1111AAA,4,false)", vehicleType));

        System.out.println("""
                \t1) brand.\

                \t2) model.\

                \t3) year (as 4 digits, from 1900 to 2050).\

                \t4) licensePlate.""");
    }

    public static void showVehicles() {
        System.out.println("\nShowing the vehicles stored:");
        System.out.println("----------------------------");

        if (vehicles.isEmpty()) {
            System.out.println("\tThere are not any stored vehicles.\n");
        } else {
            showFeatures(vehicles);
        }
    }

    // Shows all vehicles' common and specific features
    public static void showFeatures(List<Vehicle> vehicles) {
        int counter = 0;

        for (Vehicle vehicle : vehicles) {
            counter++;

            // Show vehicle counter
            System.out.println("Vehicle # " + counter);

            // Common features
            System.out.println("\tBrand: " + vehicle.getBrand());
            System.out.println("\tModel: " + vehicle.getModel());
            System.out.println("\tYear: " + vehicle.getYear());
            System.out.println("\tLicense Plate: " + vehicle.getLicensePlate());

            // Specific features
            showSpecificFeatures(vehicle);

            // Skip line
            System.out.println();
        }
        System.out.println("End of the list\n----------");
    }

    // Shows one vehicle's specific features
    public static void showSpecificFeatures(Vehicle vehicle) {
        // Get name of the vehicle from its class' name.
        //String vehicleType = vehicle.getClass().getSimpleName().toLowerCase();

        if (vehicle instanceof Car car) {
            //Car car = (Car) vehicle; // Cast to Car

            System.out.println("\tNumber of doors: " + car.getNumberOfDoors());

            boolean isConvertible = car.getConvertible();
            System.out.println("\tConvertible: " + (isConvertible ? "yes" : "no"));
        } else if (vehicle instanceof Motorbike motorbike) {
            System.out.println("\tNumber of cylinders: " + motorbike.getCylinders());

            boolean hasSidecar = motorbike.getHasSidecar();
            System.out.println("\tHas sidecar: " + (hasSidecar ? "yes" : "no"));
        }
    }

    // Identify the vehicle, validate its features and try to add the vehicle
    public static void addVehicle(String rawFeatures, String vehicleType) {
        String []features = rawFeatures.split(",");

        try {
            int nFeatures = (int) Arrays.stream(features).count();
            if (nFeatures != 6) {
                throw new Exception("You have provided " + nFeatures + " feature, " +
                        "but 6 features are the minimum required. Reseting options...\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Validate common features through vehicles
        String[] commonFeatures = Arrays.copyOfRange(features, 0, 4);

        if (!validateCommonFeatures(commonFeatures)) {
            return;
        }

        // Validate specific features for the vehicle and start addition if possible.
        String[] specificFeatures = Arrays.copyOfRange(features, 4, 6);

        if (vehicleType.equals("car") && validateCarFeatures(specificFeatures)) {
            addCar(features);
        } else if (validateMotorbikeFeatures(specificFeatures)) {
            addMotorbike(features);
        }
    }
    
    // Validate features common to all vehicles
    public static boolean validateCommonFeatures(String[] features) {
        // Validate the length of the year
        int vehicleYear;
        try {
            vehicleYear = Integer.parseInt(features[2].trim());
        } catch (Exception e) {
            System.out.println("The year is not valid a number." +
                    " Please insert a valid number. Reseting options...\n");
            return false;
        }

        // Validate length of year
        try {
            if (features[2].trim().length() != 4) {
                throw new Exception("The year value must be equal to 4 digits. " +
                        "Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Validate the range of the year
        try {
            // Validate the year 2)
            if ((vehicleYear < 1900) || (vehicleYear > 2050)) {
                throw new Exception("year value must be between 1900 and 2050. " +
                        "Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
    
    // Validate specific features for a new car
    public static boolean validateCarFeatures(String[] features) {
        int numberDoors;

        // Try to parse the number of doors from String to int
        try {
            numberDoors = Integer.parseInt(features[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("The number of doors is not a number. " +
                    "Please insert a valid one. Reseting options...\n");
            return false;
        }

        // Validate the range of Doors
        try {
            if ((numberDoors < 3) || (numberDoors > 7)) {
                throw new Exception("The number of doors value must be between 3 and 7. " +
                        "Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Validate the convertible string
        String convertible = features[1].trim();

        return validateBooleanMessage("convertible", convertible);
    }

    public static boolean validateBooleanMessage(String messageKey, String messageValue) {
        try {
            if (!POSITIVE_MESSAGE_OPTIONS.contains(messageValue) &&
                    !NEGATIVE_MESSAGE_OPTIONS.contains(messageValue)) {
                throw new Exception("The value of " + messageKey + " '" + messageValue + "'" +
                        " is not valid (one from y, yes, true, n, no or false)." +
                        " Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // Validate specific features for a new motorbike
    public static boolean validateMotorbikeFeatures(String[] features) {
        int numberCylinders;

        // Try to parse the number of cylinders from String to int
        try {
            numberCylinders = Integer.parseInt(features[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("The number of cylinders is not a number. " +
                    "Please insert a valid one. Reseting options...\n");
            return false;
        }


        // Validate the range of cylinders
        try {
            if ((numberCylinders < 1) || (numberCylinders > 12)) {
                throw new Exception("The cylinders value must be between 1 and 12. " +
                        "Reseting options...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Validate the car has a sidecar string
        String sidecar = features[1].trim();

        return validateBooleanMessage("sidecar", sidecar);
    }

    // Parser for y/yes -> true and n/no -> false
    public static boolean parseMessageToBoolean(String message) {
        if (POSITIVE_MESSAGE_OPTIONS.contains(message)) {
            return true;
        } else if (NEGATIVE_MESSAGE_OPTIONS.contains(message)) {
            return false;
        }
        return false;
    }

    // Add a car object with common and specific features
    public static void addCar(String[] features) {

        Car newCar = new Car(
                features[0],
                features[1],
                features[2],
                features[3],
                Integer.parseInt(features[4].trim()),
                parseMessageToBoolean(features[5].trim())
        );
        vehicles.add(newCar);

        System.out.println("Car vehicle added. Now there are " + vehicles.size() + " vehicles available.");
    }
    
    // Add a motorbike object with common and specific features
    public static void addMotorbike(String[] features) {
        Motorbike newMotorbike = new Motorbike(
                features[0],
                features[1],
                features[2].trim(),
                features[3],
                Integer.parseInt(features[4].trim()),
                parseMessageToBoolean(features[5].trim())
        );
        vehicles.add(newMotorbike);
        System.out.println("Motorbike vehicle added. Now there are " + vehicles.size() + " vehicles available.");
    }
}