package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFrontend {
    private List<FoodItemDTO> foodItemsList;
    private Integer userId;
    private RestaurantDTO restaurantDTO;
}
