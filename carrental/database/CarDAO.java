package com.carrental.database;

import com.carrental.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO Car (model, brand, year, price_per_day) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getBrand());
            pstmt.setInt(3, car.getYear());
            pstmt.setDouble(4, car.getPricePerDay());
            pstmt.executeUpdate();
        }
    }

    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setModel(rs.getString("model"));
                car.setBrand(rs.getString("brand"));
                car.setYear(rs.getInt("year"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                cars.add(car);
            }
        }
        return cars;
    }

    // Additional methods for updating and deleting cars
}

