package com.example.readingisgood.controller;

import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //get orders by  date interval
    @GetMapping("/dateInterval")
    public List<Order> getOrdersByDateInterval(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return orderService.getOrdersByDateInterval(startDate, endDate);
    }
    //get orders by id
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //get total amounts of orders
    @GetMapping("/totalAmount")
    public double getTotalAmountOfOrders(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Order> orders = orderService.getOrdersByDateInterval(startDate, endDate);

        double totalAmount = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        return totalAmount;
    }
}
