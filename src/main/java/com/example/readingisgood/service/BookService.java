package com.example.readingisgood.service;

import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Find a book by Id
    public Optional<Book> findBookById(Long bookId){
        return bookRepository.findById(bookId);
    }

    //Find All books exist
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    //Persist new books
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //Update a Book
    @Transactional
    public Book updateBook(Long bookId, Book updatedBook){
        Optional<Book> existingBookOptional = bookRepository.findById(bookId);
        if(existingBookOptional.isPresent()){
            Book existingBook = existingBookOptional.get();
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setCoverImageUrl(updatedBook.getCoverImageUrl());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setStockQuantity(updatedBook.getStockQuantity());

            return bookRepository.save(existingBook);
        }else{
            throw new RuntimeException("Book is not found with ID: " + bookId);
        }
    }


}
