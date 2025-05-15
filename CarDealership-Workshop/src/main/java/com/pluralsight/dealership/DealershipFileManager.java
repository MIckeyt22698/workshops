package com.pluralsight.dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = new Dealership("Mickeys Motors", "42 Wallaby Way", "555-1234");

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int vin = Integer.parseInt(fields[0]);
                int year = Integer.parseInt(fields[1]);
                String make = fields[2];
                String model = fields[3];
                String vehicleType = fields[4];
                String color = fields[5];
                int odometer = Integer.parseInt(fields[6]);
                double price = Double.parseDouble(fields[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}
