package com.pepcus.elibrary.service;

import com.pepcus.elibrary.model.BookShelf;
import com.pepcus.elibrary.repository.BookShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShelfService {

    @Autowired
    private BookShelfRepository bookShelfRepository;

    public BookShelf saveBookShelf(BookShelf bookShelf){
        return bookShelfRepository.save(bookShelf);
    }
}
