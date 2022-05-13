package com.pepcus.elibrary.repository;

import com.pepcus.elibrary.model.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf,Integer> {
}
