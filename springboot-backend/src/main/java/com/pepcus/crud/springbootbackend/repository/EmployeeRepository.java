package com.pepcus.crud.springbootbackend.repository;

import com.pepcus.crud.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JpaRepo contains API for basic CRUD operations
}
