package com.example.readingisgood.service;

import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }
    //Find a customer by Id
    public Optional<Customer> findCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }

    //Find All orders of a customer
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    //register a customer
    public Customer registerCustomer(Customer customer) { return customerRepository.save(customer); }

    public List<Order> getCustomerOrders(Long customerId, Integer page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderDate").descending());
        Page<Order> ordersPage = orderRepository.findByCustomer_Id(customerId, pageable);
        List<Order> orders = ordersPage.getContent();
        return orders;
    }


}
