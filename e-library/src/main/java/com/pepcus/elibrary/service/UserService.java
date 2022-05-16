package com.pepcus.elibrary.service;

import com.pepcus.elibrary.exception.ResourceNotFoundException;
import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.BookRepository;
import com.pepcus.elibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<String> deRegisterUser(User user, Integer id) {
        //Why the if condition was not working with null instead of isEmpty
        if (user.getBookIssuedList().isEmpty()) {
        userRepository.delete(user);
         return ResponseEntity.status(HttpStatus.OK).body("User with id-" +id+" deregistered " +
                    "successfully");
        }
        else {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User with id-" +id+" can't" +
                    " be deleted, first return the issued books");
        }
    }

    public ResponseEntity<String> issueABook(Integer uID, Integer bID) {
                Book book = bookRepository.findById(bID)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + bID));

        User user = userRepository.findById(uID)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + uID));

        user.getBookIssuedList().add(book);
        userRepository.saveAndFlush(user);

        return ResponseEntity.status(HttpStatus.OK).body(book.getBookName()+" issued successfully to"
                               +" the user "+uID);
    }

    public ResponseEntity<String> returnABook(Integer uID, Integer bID) {
        User user = userRepository.findById(uID)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + uID));

        Book book = bookRepository.findById(bID)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + bID));

        if (user.getBookIssuedList().contains(book)) {
            user.getBookIssuedList().remove(book);
            userRepository.saveAndFlush(user);
            return ResponseEntity.status(HttpStatus.OK).body(book.getBookName()+" returned successfully by"
                    +" the user "+uID);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book.getBookName()+ " not with the user "+uID);
        }
    }


}
