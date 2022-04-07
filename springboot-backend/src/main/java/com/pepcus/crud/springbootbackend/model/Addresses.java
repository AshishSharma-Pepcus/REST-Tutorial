package com.pepcus.crud.springbootbackend.model;

import lombok.*;
import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Data
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

//    @ManyToOne
//    private Employee employee;



}
