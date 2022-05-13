package com.pepcus.elibrary.repository;

import com.pepcus.elibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
