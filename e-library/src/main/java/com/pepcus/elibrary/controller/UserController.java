package com.pepcus.elibrary.controller;

import com.pepcus.elibrary.exception.ResourceNotFoundException;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.UserRepository;
import com.pepcus.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));

        userRepository.delete(user);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User with id-" +id+" deregistered " +
                "successfully");

    }


}

