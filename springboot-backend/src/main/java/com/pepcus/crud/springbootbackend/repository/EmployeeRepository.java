package com.pepcus.crud.springbootbackend.repository;

import com.pepcus.crud.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByName(String name);
    public List<Employee> findByPhoneNo(String phoneNo);
    public List<Employee> findByEmailId(String emailId);
   // public List<Employee> findByAddresses(List<Addresses> addresses);
}
