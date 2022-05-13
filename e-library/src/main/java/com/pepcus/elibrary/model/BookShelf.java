package com.pepcus.elibrary.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "BookShelf")
public class BookShelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelfId")
    private Integer shelfId;

    @Column(name = "shelfName")
    private String shelfName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Book> books = new ArrayList<>();


}
