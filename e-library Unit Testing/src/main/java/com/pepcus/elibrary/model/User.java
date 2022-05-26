package com.pepcus.elibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //User - id, name, current book id issued, registration date, deactivation date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private Date registrationDate;

    private Date deactivationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uId")
    private List<Book> bookIssuedList = new ArrayList<>();
}
