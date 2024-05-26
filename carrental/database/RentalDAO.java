package com.carrental.database;

import com.carrental.model.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
    public void addRental(Rental rental) throws SQLException {
        String sql = "INSERT INTO Rental (car_id, customer_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rental.getCarId());
            pstmt.setInt(2, rental.getCustomerId());
            pstmt.setString(3, rental.getStartDate());
            pstmt.setString(4, rental.getEndDate());
            pstmt.executeUpdate();
        }
    }

    public List<Rental> getAllRentals() throws SQLException {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM Rental";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setId(rs.getInt("id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setCustomerId(rs.getInt("customer_id"));
                rental.setStartDate(rs.getString("start_date"));
                rental.setEndDate(rs.getString("end_date"));
                rentals.add(rental);
            }
        }
        return rentals;
    }

    // Additional methods for updating and deleting rentals
}
