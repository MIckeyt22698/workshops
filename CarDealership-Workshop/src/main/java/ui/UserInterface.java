package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();  // Load dealership from file

        Scanner keyStrokes = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = keyStrokes.nextLine();

            switch (choice) {
                case "1":
                    processGetAllVehiclesRequest();
                    break;
                case "2":
                    processGetByPriceRequest();
                    break;
                case "3":
                    processGetByMakeModelRequest();
                    break;
                case "4":
                    processGetByYearRequest();
                    break;
                case "5":
                    processGetByColorRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetByMileageRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    processSellOrLeaseVehicle();
                    break;
                case "11":
                    System.out.println("Exiting program...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

        keyStrokes.close();
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

    private void displayMenu() {
        System.out.println("\n--- Dealership Menu ---");
        System.out.println("1. View All Vehicles");
        System.out.println("2. Search by Price");
        System.out.println("3. Search by Make and Model");
        System.out.println("4. Search by Year");
        System.out.println("5. Search by Color");
        System.out.println("6. Search by Vehicle Type");
        System.out.println("7. Search by Mileage");
        System.out.println("8. Add a Vehicle");
        System.out.println("9. Remove a Vehicle");
        System.out.println("10. Sell or Lease a Vehicle");
        System.out.println("11. Exit");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter minimum price: ");
            double min = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter maximum price: ");
            double max = Double.parseDouble(scanner.nextLine());

            List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
            displayVehicles(results);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }

    public void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(results);
    }


    public void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter minimum year: ");
            int minYear = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter maximum year: ");
            int maxYear = Integer.parseInt(scanner.nextLine());

            List<Vehicle> results = dealership.getVehiclesByYear(minYear, maxYear);
            displayVehicles(results);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid years.");
        }
    }

    public void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }

    public void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vehicle type (e.g., car, truck, SUV): ");
        String type = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByType(type);
        displayVehicles(results);
    }

    public void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter VIN (integer): ");
            int vin = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter make: ");
            String make = scanner.nextLine();

            System.out.print("Enter model: ");
            String model = scanner.nextLine();

            System.out.print("Enter vehicle type (e.g., car, truck): ");
            String vehicleType = scanner.nextLine();

            System.out.print("Enter color: ");
            String color = scanner.nextLine();

            System.out.print("Enter Milage reading: ");
            int mileage = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, mileage, price);
            dealership.addVehicle(newVehicle);

            System.out.println("Vehicle added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }

    public void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN of the vehicle to remove: ");
        try {
            int vinToRemove = Integer.parseInt(scanner.nextLine());

            List<Vehicle> allVehicles = dealership.getAllVehicles();
            Vehicle vehicleToRemove = null;

            for (Vehicle v : allVehicles) {
                if (v.getVin() == vinToRemove) {
                    vehicleToRemove = v;
                    break;
                }
            }

            if (vehicleToRemove != null) {
                dealership.removeVehicle(vehicleToRemove);
                System.out.println("Vehicle removed successfully.");
            } else {
                System.out.println("Vehicle with the given VIN not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN input. Please enter a number.");
        }
    }
    public void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter minimum mileage: ");
            int min = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter maximum mileage: ");
            int max = Integer.parseInt(scanner.nextLine());

            List<Vehicle> results = dealership.getVehiclesByMileage(min, max);
            displayVehicles(results);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }
}
    public void processSellOrLeaseVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN of vehicle: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Do you want to (1) Sell or (2) Lease the vehicle? Enter 1 or 2: ");
        String choice = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        switch (choice) {
            case "1":  // Sell
                System.out.print("Finance the vehicle? (yes/no): ");
                String finance = scanner.nextLine().trim().toLowerCase();
                boolean wantsFinance = finance.equals("yes");

                SalesContract salesContract = new SalesContract("2025-05-15", name, email, vehicle, wantsFinance);
                System.out.printf("Total Price: $%.2f\n", salesContract.getTotalPrice());
                System.out.printf("Monthly Payment: $%.2f\n", salesContract.getMonthlyPayment());
                dealership.removeVehicle(vehicle);
                break;

            case "2":  // Lease
                LeaseContract leaseContract = new LeaseContract("2025-05-15", name, email, vehicle);
                System.out.printf("Total Lease Fee: $%.2f\n", leaseContract.getTotalPrice());
                System.out.printf("Monthly Payment: $%.2f\n", leaseContract.getMonthlyPayment());
                dealership.removeVehicle(vehicle);
                break;

            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }
}
