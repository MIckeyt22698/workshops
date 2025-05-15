package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }

//    public void removeVehicle(String vin) {
//        for (Vehicle vehicle : inventory) {
//            if (vehicle.getVin() == vin) {
//                vehicleToRemove = vehicle;
//                break;
//        }
//
//        // If the vehicle is found, remove it
//        if (vehicleToRemove != null) {
//            inventory.remove(vehicleToRemove);
//            System.out.println("Vehicle with VIN " + vin + " removed successfully.");
//        } else {
//            System.out.println("Vehicle with VIN " + vin + " not found.");
//        }
//    }
//}
public void removeVehicle(Vehicle vehicle) {
    inventory.remove(vehicle);
}
}
