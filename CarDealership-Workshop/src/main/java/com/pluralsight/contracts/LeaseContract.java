package com.pluralsight.contracts;

import com.pluralsight.dealership.Vehicle;

public class LeaseContract extends Contract {
    private static final double endingValueRate = 0.50;
    private static final double leaseFeeRate = 0.07;
    private static final double interestRate = 0.04;
    private static final int termMonths = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * endingValueRate;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * leaseFeeRate;
    }

    @Override
    public double getTotalPrice() {
        return getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double depreciation = getVehicleSold().getPrice() - getExpectedEndingValue();
        double interest = depreciation * interestRate;
        return (depreciation + interest) / termMonths;
    }
}