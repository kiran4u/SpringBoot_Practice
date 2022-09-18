package com.kiran.controller;

import com.kiran.model.Order;
import com.kiran.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
