package com.pluralsight.contracts;

import com.pluralsight.dealership.Vehicle;

public class SalesContract extends Contract {
    private static final double salesTax = 0.05;
    private static final double recordingFee = 100.00;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    public double getSalesTaxAmount() {
        return getVehicleSold().getPrice() * salesTax;
    }

    public double getProcessingFee() {
        return getVehicleSold().getPrice() < 10000 ? 295.00 : 495.00;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        return price + getSalesTaxAmount() + recordingFee + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;

        double totalPrice = getTotalPrice();
        double interestRate;
        int term;

        if (getVehicleSold().getPrice() >= 10000) {
            interestRate = 0.0425;
            term = 48;
        } else {
            interestRate = 0.0525;
            term = 24;
        }

        return (totalPrice * (1 + interestRate)) / term;
    }

    public double getRecordingFee() {
        return this.recordingFee;
    }
}

