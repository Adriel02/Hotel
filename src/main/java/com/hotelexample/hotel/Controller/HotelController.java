package com.hotelexample.hotel.Controller;


import com.hotelexample.hotel.model.Hotel;
import com.hotelexample.hotel.dao.HotelRepository;
import com.hotelexample.hotel.model.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAll(){
        List<Hotel> allHotels = this.hotelRepository.findAll();
        return allHotels;
    }
    @PutMapping
    public void insert(@RequestBody Hotel hotel){
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void update(@RequestBody Hotel hotel){
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getById(@PathVariable("id") String id){
        Optional<Hotel> hotelsById = this.hotelRepository.findById(id);
        return hotelsById;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> findByPricePerNighht(@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotelsByPrice = this.hotelRepository.findByPricePerNighhtLessThan(maxPrice);
        return hotelsByPrice;
    }


    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city){
        List<Hotel> hotelsByCity = this.hotelRepository.findByCity(city);
        return hotelsByCity;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country")String country){
        //Create a query
        QHotel qhotel = new QHotel("hotel");

        //Create a filter
        BooleanExpression filterByCountry = qhotel.address.country.eq(country);

        //user the findall with our filter
        List<Hotel> hotelsByFilter = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
        return hotelsByFilter;
    }

     @GetMapping("/recommended")
    public List<Hotel> getRecommended(){
        final int maxPrice= 100;
        final int minRating =7;

        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNighht.lt(maxPrice); //Price less than maxPrice
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating); // Some review that his rating is geater than minRating

         return (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));

    }


    @GetMapping("/notRecommended")
    public List<Hotel> getBadhotel(){
        final int maxPrice = 150;
        final int maxRating = 5;

        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByBadPrice = qHotel.pricePerNighht.gt(maxPrice);
        BooleanExpression filterByBadRating = qHotel.reviews.any().rating.lt(maxRating);

        return (List<Hotel>) this.hotelRepository.findAll(filterByBadPrice.and(filterByBadRating));

    }

}
