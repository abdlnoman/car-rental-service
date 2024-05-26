package com.carrentmanagement;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CarRentManagement {
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        RentalDAO rentalDAO = new RentalDAO();
        CarDAO carDAO = new CarDAO();
        Scanner scanner = new Scanner(System.in);

        insertRandomCars(carDAO);

        System.out.println("Welcome to Car Rent Management System");

        // Login loop
        while (!authService.isLoggedIn()) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (authService.login(username, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Post-login operations
        User loggedInUser = authService.getLoggedInUser();
        System.out.println("Welcome, " + loggedInUser.getUsername());

        // Menu loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. View My Rentals");
            System.out.println("2. View Available Cars");
            System.out.println("3. Rent a Car");
            System.out.println("4. Return a Car");
            System.out.println("5. Show Car Details");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View My Rentals
                    List<Rental> rentals = rentalDAO.getRentalsByUserId(loggedInUser.getId());
                    System.out.println("My Rentals:");
                    for (Rental rental : rentals) {
                        System.out.println(rental);
                    }
                    break;
                case 2:
                    // View Available Cars
                    List<Car> availableCars = carDAO.getAvailableCars();
                    System.out.println("Available Cars:");
                    for (Car car : availableCars) {
                        System.out.println(car);
                    }
                    break;
                case 3:
                    // Rent a Car
                    System.out.print("Enter car ID to rent: ");
                    int carIdToRent = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Rental newRental = new Rental();
                    newRental.setUserId(loggedInUser.getId());
                    newRental.setCarId(carIdToRent);
                    newRental.setRentDate(new Date());
                    newRental.setReturnDate(null);

                    boolean rentalAdded = rentalDAO.addRental(newRental);
                    if (rentalAdded) {
                        System.out.println("Car rented successfully!");
                    } else {
                        System.out.println("Failed to rent the car. Please try again.");
                    }
                    break;
                case 4:
                    // Return a Car
                    System.out.print("Enter rental ID to return: ");
                    int rentalIdToReturn = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    boolean returnUpdated = rentalDAO.updateReturnDate(rentalIdToReturn, new Date());
                    if (returnUpdated) {
                        System.out.println("Car returned successfully!");
                    } else {
                        System.out.println("Failed to return the car. Please try again.");
                    }
                    break;
                case 5:
                    // Show Car Details
                    System.out.print("Enter car ID to view details: ");
                    int carIdToShow = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Car carDetails = carDAO.getCarById(carIdToShow);
                    if (carDetails != null) {
                        System.out.println("Car Details:");
                        System.out.println("ID: " + carDetails.getId());
                        System.out.println("Model: " + carDetails.getModel());
                        System.out.println("Brand: " + carDetails.getBrand());
                        System.out.println("Availability: " + (carDetails.isAvailability() ? "Available" : "Not Available"));
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 6:
                    // Logout
                    authService.logout();
                    System.out.println("You have been logged out.");
                    exit = true;
                    break;
                case 7:
                    // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private static void insertRandomCars(CarDAO carDAO) {
        Car corolla = new Car(1, "Corolla", "Toyota", true);
        carDAO.insertCar(corolla);

        Car civic = new Car(2, "Civic", "Honda", true);
        carDAO.insertCar(civic);

        Car mustang = new Car(3, "Mustang", "Ford", true);
        carDAO.insertCar(mustang);

        Car malibu = new Car(4, "Malibu", "Chevrolet", true);
        carDAO.insertCar(malibu);

        Car altima = new Car(5, "Altima", "Nissan", true);
        carDAO.insertCar(altima);

        Car bmw3 = new Car(6, "3 Series", "BMW", true);
        carDAO.insertCar(bmw3);

        Car eClass = new Car(7, "E-Class", "Mercedes-Benz", true);
        carDAO.insertCar(eClass);

        Car a4 = new Car(8, "A4", "Audi", true);
        carDAO.insertCar(a4);

        Car optima = new Car(9, "Optima", "Kia", true);
        carDAO.insertCar(optima);
    }
}
