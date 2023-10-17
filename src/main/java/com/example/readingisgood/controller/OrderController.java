package com.example.readingisgood.controller;

import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //get orders by  date interval
    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<List<Order>> getOrdersByDateInterval(@PathVariable Date startDate, @PathVariable Date endDate){
        List<Order> orders = orderService.getOrdersByDateInterval(startDate,endDate);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    //get orders by id
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
