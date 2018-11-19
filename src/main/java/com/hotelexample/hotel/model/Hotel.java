package com.hotelexample.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int pricePerNighht;
    private Address address;
    private List<Review> reviews;

    protected Hotel(){
        reviews= new ArrayList<>();
    }

    public Hotel(String name, int pricePerNighht, Address address, List<Review> reviews) {
        this.name = name;
        this.pricePerNighht = pricePerNighht;
        this.address = address;
        this.reviews = reviews;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerNighht() {
        return pricePerNighht;
    }

    public Address getAddress() {
        return address;
    }

    public List<Review> getReviews() {
        return reviews;
    }


}
