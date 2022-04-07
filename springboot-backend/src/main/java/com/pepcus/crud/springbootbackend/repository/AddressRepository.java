package com.pepcus.crud.springbootbackend.repository;

import com.pepcus.crud.springbootbackend.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses,Long> {

}
