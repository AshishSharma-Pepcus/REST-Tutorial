package com.pepcus.elibrary.controller;

import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.BookShelf;
import com.pepcus.elibrary.repository.BookShelfRepository;
import com.pepcus.elibrary.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-shelves")
public class BookShelfController {

    @Autowired
    private BookShelfService bookShelfService;
    @Autowired
    private BookShelfRepository bookShelfRepository;

    @PostMapping
    public BookShelf createBookShelf(@RequestBody BookShelf bookShelf) {
        return bookShelfService.saveBookShelf(bookShelf);
    }


    @PatchMapping("{id}/{userId}")
    public BookShelf addBook(@PathVariable Integer id, @RequestBody List<Book> books) {
        BookShelf bookShelf = bookShelfRepository.getById(id);
        List<Book> existingBookList = bookShelf.getBooks();
        for (Book book : books ) {
            existingBookList.add(book);
        }
        return bookShelfRepository.save(bookShelf);
    }

}
