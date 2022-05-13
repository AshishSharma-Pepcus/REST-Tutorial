package com.pepcus.elibrary.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer bookId;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "addedDate")
    private Date addedDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "deletedDate")
    private Date deletedDate;

}
