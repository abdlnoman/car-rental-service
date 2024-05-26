package com.carrentmanagement;

import java.sql.SQLException;

public class AuthenticationService {
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser;

    // Login method
    public boolean login(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Logout method
    public void logout() {
        loggedInUser = null;
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Get the logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
