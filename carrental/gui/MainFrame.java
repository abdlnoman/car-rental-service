package com.carrental.gui;

import com.carrental.database.CarDAO;
import com.carrental.model.Car;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private CarDAO carDAO;

    public MainFrame() {
        carDAO = new CarDAO();

        setTitle("Car Rental System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add components
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Car Rental System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Add car listing panel
        JPanel carListPanel = new JPanel();
        carListPanel.setLayout(new BoxLayout(carListPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(carListPanel), BorderLayout.CENTER);

        // Load and display cars
        try {
            List<Car> cars = carDAO.getAllCars();
            for (Car car : cars) {
                carListPanel.add(new JLabel(car.getModel() + " - " + car.getBrand()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading cars", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add more panels and components as needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
