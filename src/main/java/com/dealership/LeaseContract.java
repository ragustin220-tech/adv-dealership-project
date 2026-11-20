package com.dealership;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);

        double vehiclePrice = vehicleSold.getPrice().doubleValue();
        this.expectedEndingValue = vehiclePrice * 0.50;
        this.leaseFee = vehiclePrice * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        totalPrice = expectedEndingValue + leaseFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        double principal = getTotalPrice();
        double monthlyRate = 0.04 / 12; // 4.0% annual rate
        int months = 36;

        monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);

        return monthlyPayment;
    }
}

