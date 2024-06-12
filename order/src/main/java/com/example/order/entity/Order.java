package com.example.order.entity;

import com.example.order.dto.FoodItemDTO;
import com.example.order.dto.RestaurantDTO;
import com.example.order.dto.UserDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemDTO> foodItemsList;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
