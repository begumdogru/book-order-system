package com.example.readingisgood.controller;
import com.example.readingisgood.model.MonthlyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.readingisgood.service.OrderService;

@RestController
public class StatisticsController {

    private final OrderService orderService;

    @Autowired
    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<MonthlyStatistics> getMonthlyStatistics(@RequestParam int year, @RequestParam int month) {

        MonthlyStatistics monthlyStatistics = orderService.getMonthlyStatistics(year, month);

        return ResponseEntity.ok(monthlyStatistics);
    }
}
