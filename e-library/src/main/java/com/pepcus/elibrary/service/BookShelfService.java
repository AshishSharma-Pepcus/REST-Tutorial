package com.pepcus.elibrary.service;

import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.BookShelf;
import com.pepcus.elibrary.repository.BookShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShelfService {

    @Autowired
    private BookShelfRepository bookShelfRepository;

    public BookShelf saveBookShelf(BookShelf bookShelf){
        return bookShelfRepository.save(bookShelf);
    }

    public BookShelf addBooksToShelf(Integer shelfId, List<Book> books) {
                BookShelf bookShelf = bookShelfRepository.getById(shelfId);
        List<Book> existingBookList = bookShelf.getBooks();
        for (Book book : books ) {
            existingBookList.add(book);
        }
        return bookShelfRepository.save(bookShelf);
    }
}
