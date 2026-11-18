package com.dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class DealershipFileManager {

    private static final String FILE_PATH = "Inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String titleLine = reader.readLine();
            if (titleLine != null) {
                String[] dealershipParts = titleLine.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                dealership = new Dealership(name, address, phone);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                BigDecimal price = new BigDecimal(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                if (dealership != null) {
                    dealership.addVehicle(vehicle);
                }
            }

        } catch (IOException e) {
            System.out.println("Couldn't read file : " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}