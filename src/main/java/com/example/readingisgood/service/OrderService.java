package com.example.readingisgood.service;

import com.example.readingisgood.exception.InsufficientStockException;
import com.example.readingisgood.exception.ProductNotFoundException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.MonthlyStatistics;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.model.OrderItem;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }
    //Query orders by ID
    public Optional<Order> getOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }
    //List orders by date interval
    public List<Order> getOrdersByDateInterval(Date startDate, Date endDate){
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
    //Persist new order
    public Order addOrder(Order order) {
        order.setOrderStatus("Pending");
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    @Transactional
    public void updateStock(Order order){
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem: orderItems){
            Book book = orderItem.getBook();
            int quantityOrdered = orderItem.getQuantity();
            int currentStock = book.getStockQuantity();

            if(currentStock >= quantityOrdered){
                book.setStockQuantity(currentStock - quantityOrdered);
                bookRepository.save(book);
            }else{
                throw new InsufficientStockException("Insufficient stock for book ID: " + book.getId());

            }
        }
    }
    @Transactional
    public void purchaseLastBook(Optional<Book> book) {
        if(book.isPresent()){
            Book actualBook = book.get();
            int currentStock = actualBook.getStockQuantity();
            if (currentStock > 0) {
                actualBook.setStockQuantity(currentStock - 1);
                bookRepository.save(actualBook);
            } else {
                throw new InsufficientStockException("The book is out of stock.");
            }
        }else{
            throw new ProductNotFoundException("Book not found.");
        }
    }
    public MonthlyStatistics getMonthlyStatistics(int year, int month) {
        List<Order> orders = orderRepository.findByYearAndMonth(year, month);

        long totalOrderCount = orders.size();
        double totalOrderAmount = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        long totalBookCount = orders.stream().flatMap(order -> order.getOrderItems().stream())
                .mapToLong(orderItem -> orderItem.getQuantity()).sum();

        return new MonthlyStatistics(totalOrderCount, totalOrderAmount, totalBookCount);
    }
}
