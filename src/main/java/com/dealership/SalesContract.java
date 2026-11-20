package com.dealership;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;

        double vehiclePrice = vehicleSold.getPrice().doubleValue();
        this.salesTaxAmount = vehiclePrice * 0.05;
        this.recordingFee = 100.00;

        if (vehiclePrice < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        totalPrice = getVehicleSold().getPrice().doubleValue() + salesTaxAmount + recordingFee + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            monthlyPayment = 0.0;
            return monthlyPayment;
        }

        double principal = getTotalPrice();
        double annualRate;
        int months;

        if (getVehicleSold().getPrice().doubleValue() >= 10000) {
            annualRate = 0.0425; // 4.25%
            months = 48;
        } else {
            annualRate = 0.0525; // 5.25%
            months = 24;
        }

        double monthlyRate = annualRate / 12;
        monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);

        return monthlyPayment;
    }
}
