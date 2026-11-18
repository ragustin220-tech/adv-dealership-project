package com.dealership;

import java.math.BigDecimal;
import java.util.List;
public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    public void display() {
        init();
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = Console.promptForInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMilageRequest();
                    break;
                case 6:
                    processGetByVehicleRequest();
                    break;
                case 7:
                    processGetAllVehicleRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    running = false;
                    System.out.println("Exiting application.Goodbye!");
                    break;
                default:
                    System.out.println("Please choose a number listed.");

            }
        }
    }
    private void displayMenu() {
        System.out.println("\n--- " + dealership.getName() + "---");
        System.out.println("""
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicle by type (car, truck, SUV, van)
                7 - List All vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                """);
    }
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found matching your criteria.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.printf("VIN: %d | %d %s %s | Type: %s | Color: %s | Odometer: %,d | Price: $%.2f%n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    public void processGetAllVehicleRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processGetByPriceRequest() {
        BigDecimal min = Console.promptForBigDecimal("Enter minimum price: ");
        BigDecimal max = Console.promptForBigDecimal("Enter maximum price: ");
        List<Vehicle> results = dealership.getVehicleByPrice(min, max);
        displayVehicles(results);
    }

    public void processGetByMakeModelRequest() {
        String make = Console.promptForString("Enter make: ");
        String model = Console.promptForString("Enter model: ");
        List<Vehicle> results = dealership.getVehicleByMakeModel(make,model);
        displayVehicles(results);
    }

    public void processGetByYearRequest() {
        int min = Console.promptForInt("Enter minimum year: ");
        int max = Console.promptForInt("Enter maximum year: ");
        List<Vehicle> results = dealership.getVehicleByYear(min, max);
        displayVehicles(results);
    }

    public void processGetByColorRequest() {
        String color = Console.promptForString("Enter color: ");
        List<Vehicle>results = dealership.getVehicleByColor(color);
        displayVehicles(results);
    }

    public void processGetByMilageRequest() {
        int min = Console.promptForInt("Enter minimum mileage: ");
        int max = Console.promptForInt("Enter maximum mileage: ");
        List<Vehicle> results = dealership.getVehicleByMileage(min,max);
        displayVehicles(results);
    }

    public void processGetByVehicleRequest() {
        String type = Console.promptForString("Enter vehicle type: ");
        List<Vehicle> results = dealership.getVehicleByType(type);
        displayVehicles(results);
    }

    public void processAddVehicleRequest() {
        int vin = Console.promptForInt("Enter VIN: ");
        int year = Console.promptForInt("Enter year: ");
        String make = Console.promptForString("Enter make: ");
        String model = Console.promptForString("Enter model: ");
        String type = Console.promptForString("Enter vehicle type:" );
        String color = Console.promptForString("Enter color: ");
        int odometer = Console.promptForInt("Enter odometer: ");
        BigDecimal price = Console.promptForBigDecimal("Enter price: ");

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer,price );
        dealership.addVehicle(newVehicle);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added successfully!");
    }

    public void processRemoveVehicleRequest() {
        int vin = Console.promptForInt("Enter the VIN of the vehicle you want to remove: ");
        Vehicle vehicleToRemove = null;

        for (Vehicle v : dealership.getAllVehicles()){
            if (v.getVin() == vin) {
            vehicleToRemove = v;
            break;
            }
        }
        if (vehicleToRemove != null){
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager fileManager= new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle Removed.");
        }else{
            System.out.println("No vehicle found with VIN" + vin +".");
        }
    }
}




