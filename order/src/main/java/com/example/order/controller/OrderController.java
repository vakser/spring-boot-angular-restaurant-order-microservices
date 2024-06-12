package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderDTOFrontend;
import com.example.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFrontend orderDetails) {
        OrderDTO orderSavedInDB = orderService.saveOrderInDb(orderDetails);
        return new ResponseEntity<>(orderSavedInDB, HttpStatus.CREATED);
    }
}
