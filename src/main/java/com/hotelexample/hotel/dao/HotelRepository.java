package com.hotelexample.hotel.dao;

import com.hotelexample.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String>, QueryDslPredicateExecutor<Hotel> {
    Optional<Hotel> findById(String id);
    List<Hotel> findByPricePerNighhtLessThan(int maxPrice);

    @Query(value = "{'address.city':?0}")
    List<Hotel> findByCity(String city);
}
