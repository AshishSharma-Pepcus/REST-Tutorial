package com.pepcus.crud.springbootbackend;

import com.pepcus.crud.springbootbackend.repository.EmployeeRepository;
import com.pepcus.crud.springbootbackend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setName("Ashish");
		employee.setPhoneNo("12345");
		employee.setEmailId("ashish@gmail.com");
		employee.setAddress("Bhawarkuan");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setName("Sooraj");
		employee1.setPhoneNo("67890");
		employee1.setEmailId("sooraj@gmail.com");
		employee1.setAddress("Sector-16");
		employeeRepository.save(employee1);

	}
}