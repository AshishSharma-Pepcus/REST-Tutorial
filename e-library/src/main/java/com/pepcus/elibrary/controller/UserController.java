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
//        Book book = bookRepository.findById(bID)
//                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + bID));
//
//        User user = userRepository.findById(uID)
//                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + uID));
//
//        user.getBookIssuedList().add(book);
//        userRepository.saveAndFlush(user);
//
//        return ResponseEntity.status(HttpStatus.OK).body(book.getBookName()+" issued successfully to"
//                               +" the user "+uID);
    }

    @PatchMapping("book-return/{uID}/{bID}")
    public ResponseEntity<String> returnBook(@PathVariable Integer uID, @PathVariable Integer bID) {
        return userService.returnABook(uID,bID);

//        User user = userRepository.findById(uID)
//                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + uID));
//        Book book = bookRepository.findById(bID)
//                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + bID));
//        if (user.getBookIssuedList().contains(book)) {
//
//            user.getBookIssuedList().remove(book);
//            userRepository.saveAndFlush(user);
//            return ResponseEntity.status(HttpStatus.OK).body(book.getBookName()+" returned successfully by"
//                    +" the user "+uID);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book.getBookName()+ " not with the user "+uID);
//        }
    }
}

