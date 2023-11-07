package com.example.readingisgood.repository;

import com.example.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByCustomer_Id(Long customerId, Pageable pageable);
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
    List<Order> findByYearAndMonth(int year, int month);

}
