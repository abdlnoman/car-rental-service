package com.carrentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
    
    // Add a new rental to the database
    public boolean addRental(Rental rental) {
        String sql = "INSERT INTO Rentals (user_id, car_id, rent_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, rental.getUserId());
            statement.setInt(2, rental.getCarId());
            statement.setDate(3, new java.sql.Date(rental.getRentDate().getTime()));
            statement.setDate(4, rental.getReturnDate() != null ? new java.sql.Date(rental.getReturnDate().getTime()) : null);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update the return date of a rental
    public boolean updateReturnDate(int rentalId, java.util.Date returnDate) {
        String sql = "UPDATE Rentals SET return_date = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(returnDate.getTime()));
            statement.setInt(2, rentalId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a rental by its ID
    public Rental getRentalById(int rentalId) {
        String sql = "SELECT * FROM Rentals WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, rentalId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Rental rental = new Rental();
                rental.setId(resultSet.getInt("id"));
                rental.setUserId(resultSet.getInt("user_id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setRentDate(resultSet.getDate("rent_date"));
                rental.setReturnDate(resultSet.getDate("return_date"));
                return rental;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all rentals for a specific user
    public List<Rental> getRentalsByUserId(int userId) {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM Rentals WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Rental rental = new Rental();
                rental.setId(resultSet.getInt("id"));
                rental.setUserId(resultSet.getInt("user_id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setRentDate(resultSet.getDate("rent_date"));
                rental.setReturnDate(resultSet.getDate("return_date"));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // Get all rentals
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM Rentals";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Rental rental = new Rental();
                rental.setId(resultSet.getInt("id"));
                rental.setUserId(resultSet.getInt("user_id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setRentDate(resultSet.getDate("rent_date"));
                rental.setReturnDate(resultSet.getDate("return_date"));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
}
