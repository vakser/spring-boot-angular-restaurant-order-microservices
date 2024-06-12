package com.example.restaurantlisting.service;

import com.example.restaurantlisting.dto.RestaurantDTO;
import com.example.restaurantlisting.entity.Restaurant;
import com.example.restaurantlisting.mapper.RestaurantMapper;
import com.example.restaurantlisting.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO).collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant =  restaurantRepository.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        return restaurant.map(value -> new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(value),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
