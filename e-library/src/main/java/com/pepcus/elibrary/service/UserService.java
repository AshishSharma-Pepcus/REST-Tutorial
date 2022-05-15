package com.pepcus.elibrary.service;

import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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


}
