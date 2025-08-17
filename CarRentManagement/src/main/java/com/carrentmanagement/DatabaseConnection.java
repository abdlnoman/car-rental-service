package com.carrentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CarRentManagement";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver class is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
Update at 2024-01-03 01:01:02
Update at 2024-01-07 08:54:46
Update at 2024-01-11 08:27:57
Update at 2024-03-16 12:59:35
Update at 2024-04-24 00:13:59
Update at 2024-05-22 13:40:31
2024-05-29: ..............................
