package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderDTOFrontend;
import com.example.order.dto.UserDTO;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, SequenceGenerator sequenceGenerator, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
    }

    public OrderDTO saveOrderInDb(OrderDTOFrontend orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurantDTO(), userDTO );
        orderRepository.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}
