package com.pluralsight.contracts;

import com.pluralsight.dealership.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContractFileManager {

    private static final String filePath = "contracts.txt";
    private static final String delimiter = "\\|";  // for splitting lines
    private static final String writeDelimiter = "|"; // for writing lines

    public static void saveContracts(List<Contract> contracts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contract contract : contracts) {
                StringBuilder sb = new StringBuilder();

                if (contract instanceof SalesContract) {
                    SalesContract salesContract = (SalesContract) contract;
                    sb.append("sale").append(writeDelimiter);
                    sb.append(salesContract.getDate()).append(writeDelimiter);
                    sb.append(salesContract.getCustomerName()).append(writeDelimiter);
                    sb.append(salesContract.getCustomerEmail()).append(writeDelimiter);
                    sb.append(salesContract.getVehicleSold().getVin()).append(writeDelimiter);
                    sb.append(salesContract.getVehicleSold().getPrice()).append(writeDelimiter);
                    sb.append(salesContract.getSalesTaxAmount()).append(writeDelimiter);
                    sb.append(salesContract.getRecordingFee()).append(writeDelimiter);
                    sb.append(salesContract.getProcessingFee()).append(writeDelimiter);
                    sb.append(salesContract.isFinance());
                } else if (contract instanceof LeaseContract) {
                    LeaseContract leaseContract = (LeaseContract) contract;
                    sb.append("lease").append(writeDelimiter);
                    sb.append(leaseContract.getDate()).append(writeDelimiter);
                    sb.append(leaseContract.getCustomerName()).append(writeDelimiter);
                    sb.append(leaseContract.getCustomerEmail()).append(writeDelimiter);
                    sb.append(leaseContract.getVehicleSold().getVin()).append(writeDelimiter);
                    sb.append(leaseContract.getVehicleSold().getPrice()).append(writeDelimiter);
                    sb.append(leaseContract.getExpectedEndingValue()).append(writeDelimiter);
                    sb.append(leaseContract.getLeaseFee());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
            System.out.println("Contracts saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving contracts: " + e.getMessage());
        }
    }

    public static List<Contract> loadContracts(List<Vehicle> vehicles) {
        List<Contract> contracts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(delimiter);
                if (parts.length < 6) continue; // basic validation

                String contractType = parts[0];
                String date = parts[1];
                String customerName = parts[2];
                String customerEmail = parts[3];
                int vin = Integer.parseInt(parts[4]);

                // Find vehicle by VIN
                Vehicle vehicle = null;
                for (Vehicle v : vehicles) {
                    if (v.getVin() == vin) {
                        vehicle = v;
                        break;
                    }
                }
                if (vehicle == null) {
                    System.err.println("Vehicle with VIN " + vin + " not found for contract.");
                    continue;
                }

                if ("sale".equals(contractType)) {
                    double salesTaxAmount = Double.parseDouble(parts[6]);
                    double recordingFee = Double.parseDouble(parts[7]);
                    double processingFee = Double.parseDouble(parts[8]);
                    boolean financed = Boolean.parseBoolean(parts[9]);

                    SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicle, financed);

                    contracts.add(salesContract);

                } else if ("lease".equals(contractType)) {
                    double expectedEndingValue = Double.parseDouble(parts[6]);
                    double leaseFee = Double.parseDouble(parts[7]);

                    LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle);

                    contracts.add(leaseContract);
                }
            }
            System.out.println("Contracts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No contracts file found, starting fresh.");
        } catch (IOException e) {
            System.err.println("Error reading contracts: " + e.getMessage());
        }
        return contracts;
    }
}
