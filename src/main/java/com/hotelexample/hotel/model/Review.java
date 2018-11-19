package com.hotelexample.hotel.model;

public class Review {

    private String userName;
    private int rating;
    private boolean approved;

    protected Review(){}

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public boolean isApproved() {
        return approved;
    }

    public Review(String userName, int rating, boolean approved) {
        this.userName = userName;
        this.rating = rating;
        this.approved = approved;
    }
}
