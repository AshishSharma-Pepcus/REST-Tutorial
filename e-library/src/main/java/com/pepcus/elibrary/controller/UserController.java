package com.pepcus.elibrary.controller;

import com.pepcus.elibrary.exception.ResourceNotFoundException;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.BookRepository;
import com.pepcus.elibrary.repository.UserRepository;
import com.pepcus.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));

        return userService.deRegisterUser(user,userId);
    }

    @PatchMapping("book-issue/{userId}/{bookId}")
    public ResponseEntity<String> issueBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        return userService.issueABook(userId,bookId);
    }

    @PatchMapping("book-return/{userId}/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        return userService.returnABook(userId,bookId);
    }
}

