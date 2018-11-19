package com.hotelexample.hotel.dao;

import com.hotelexample.hotel.model.Address;
import com.hotelexample.hotel.model.Hotel;
import com.hotelexample.hotel.model.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args){
        Hotel hotel1 = new Hotel(
                "IFA",
                130,
                new Address("Galdar", "España"),
                Arrays.asList(
                        new Review("Adriel", 8, false),
                        new Review("Daniel", 9, true)
                )

        );
        Hotel hotel2 = new Hotel(
                "Regina",
                180,
                new Address("Las Palmas", "Italy"),
                Arrays.asList(
                        new Review("Juan", 6, false),
                        new Review("Alberto", 8, true)
                )

        );
        Hotel hotel3 = new Hotel(
                "Fustina",
                80,
                new Address("Galdar", "EEUU"),
                Arrays.asList(
                        new Review("Raul", 6, false),
                        new Review("Lorenzo", 10, true)
                )

        );


        //Drop all hotels
        this.hotelRepository.deleteAll();

        //Insert 1 hotel
        List<Hotel> hotels = Arrays.asList(hotel1,hotel2,hotel3);
        this.hotelRepository.save(hotels);
    }
}
