package com.carrentmanagement;

import java.util.Date;

public class Rental {
    private int id;
    private int userId;
    private int carId;
    private Date rentDate;
    private Date returnDate;

    // Default constructor
    public Rental() {}

    // Parameterized constructor
    public Rental(int id, int userId, int carId, Date rentDate, Date returnDate) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", rentDate=" + rentDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
