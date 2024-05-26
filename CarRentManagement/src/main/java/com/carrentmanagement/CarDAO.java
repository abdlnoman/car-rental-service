package com.carrentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public boolean insertCar(Car car) {
        String sql = "INSERT INTO Cars (model, brand, availability) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getBrand());
            statement.setBoolean(3, car.isAvailability());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        String sql = "SELECT * FROM Cars WHERE availability = true";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                String brand = resultSet.getString("brand");
                boolean availability = resultSet.getBoolean("availability");
                availableCars.add(new Car(id, model, brand, availability));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCars;
    }

   public Car getCarById(int carId) {
        String sql = "SELECT * FROM Cars WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String model = resultSet.getString("model");
                    String brand = resultSet.getString("brand");
                    boolean availability = resultSet.getBoolean("availability");
                    return new Car(id, model, brand, availability);
                } else {
                    return null; // Car not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Error occurred
        }
    }
}





