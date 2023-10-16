package com.example.readingisgood.repository;

import com.example.readingisgood.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BookRepository implements JpaRepository<Book,Long> {
}
