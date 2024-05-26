package com.carrental;

import com.carrental.database.DatabaseConnection;
import com.carrental.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Initialize the database
        DatabaseConnection.initializeDatabase();
        
        // Start the GUI
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
