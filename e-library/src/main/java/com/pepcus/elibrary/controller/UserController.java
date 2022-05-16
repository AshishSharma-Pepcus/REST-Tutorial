package com.pepcus.elibrary.controller;

import com.pepcus.elibrary.exception.ResourceNotFoundException;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.BookRepository;
import com.pepcus.elibrary.repository.UserRepository;
import com.pepcus.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));

        return userService.deRegisterUser(user,id);
    }

    @PatchMapping("book-issue/{uID}/{bID}")
    public ResponseEntity<String> issueBook(@PathVariable Integer uID, @PathVariable Integer bID) {
        return userService.issueABook(uID,bID);
    }

    @PatchMapping("book-return/{uID}/{bID}")
    public ResponseEntity<String> returnBook(@PathVariable Integer uID, @PathVariable Integer bID) {
        return userService.returnABook(uID,bID);
    }
}

