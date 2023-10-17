package com.example.readingisgood.controller;

import com.example.readingisgood.exception.InsufficientStockException;
import com.example.readingisgood.exception.ProductNotFoundException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final OrderService orderService;

    @Autowired
    public BookController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    //get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    //Get book by id
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        Optional<Book> book = bookService.findBookById(bookId);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book addedBook = bookService.addBook(book);
        return new ResponseEntity<>(addedBook, HttpStatus.OK);
    }
    //Update a book
    @PostMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook){
        Book updated = bookService.updateBook(bookId,updatedBook);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @PostMapping("/{bookId}/purchase")
    public ResponseEntity<String> purchaseLastBook(@PathVariable Long bookId) {
        try {
            Optional<Book> book = bookService.findBookById(bookId);
            orderService.purchaseLastBook(book);
            return ResponseEntity.ok("Book purchased successfully.");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock for the book.");
        }
    }


}
