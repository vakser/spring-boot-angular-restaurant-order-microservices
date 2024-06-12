package com.example.restaurantlisting.repository;

import com.example.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
