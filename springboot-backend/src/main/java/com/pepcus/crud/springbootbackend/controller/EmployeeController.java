package com.pepcus.crud.springbootbackend.controller;

import com.pepcus.crud.springbootbackend.exception.ResourceNotFoundException;
import com.pepcus.crud.springbootbackend.model.Employee;
import com.pepcus.crud.springbootbackend.repository.AddressRepository;
import com.pepcus.crud.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api/v1/employees", "/api/v1/addresses", "/api/v1/employees_address"})
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

//        Employee employee1 = new Employee();
//
//       List<Addresses> addressList = new ArrayList<>();
//       employee.getAddresses().stream().map(a ->addressList.add(a)).collect(Collectors.toList());
//
//       employee.setAddresses(addressList);
//
//        employee1.setAddresses(addressList);
//        employee1.setName(employee.getName());
//        employee1.setPhoneNo(employee.getPhoneNo());
//        employee1.setEmailId(employee.getEmailId());

        //return employeeRepository.save(employee1);
        return employeeRepository.save(employee);
    }


    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }


    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setName(employeeDetails.getName());
        updateEmployee.setPhoneNo(employeeDetails.getPhoneNo());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        updateEmployee.setAddresses(employeeDetails.getAddresses());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}