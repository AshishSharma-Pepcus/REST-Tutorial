package com.pepcus.elibrary.service;

import com.pepcus.elibrary.exception.ResourceNotFoundException;
import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.BookRepository;
import com.pepcus.elibrary.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

//    @Mock
//    private Optional<Book> book;
//
//    @Mock
//    private Optional<User> user;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        User userRecord = new User(1,"Ashish",new Date(),new Date(),null);
        Mockito.when(userRepository.save(userRecord)).thenReturn(userRecord);
        User user = userService.saveUser(userRecord);
        Assert.assertEquals(userRecord,user);
    }

    @Test
    public void testSuccessfulDeregisterUser() {
        List<Book> issuedBookList = new ArrayList<>();
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);

       // Mockito.when(userRecord.getBookIssuedList().isEmpty()).thenReturn(true);

        ResponseEntity<String> actual = userService.deRegisterUser(userRecord,userRecord.getUserId());

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body("User with id-" +
                userRecord.getUserId() + " deregistered " + "successfully");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testUnsuccessfulDeregistration() {
        List<Book> issuedBookList = new ArrayList<>();
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());
        issuedBookList.add(bookRecord);
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);

        ResponseEntity<String> actual = userService.deRegisterUser(userRecord,userRecord.getUserId());

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("User with id-" + userRecord.getUserId() + " can't" + " be deleted, first return the issued books");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSuccessfulIssueBook() {
        List<Book> issuedBookList = new ArrayList<>();
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());

        Mockito.when(bookRepository.findById(bookRecord.getBookId())).thenReturn(Optional.of(bookRecord));
        Mockito.when(userRepository.findById(userRecord.getUserId())).thenReturn(Optional.of(userRecord));

        Optional<Book> book = bookRepository.findById(bookRecord.getBookId());
        Optional<User> user = userRepository.findById(userRecord.getUserId());

       // Mockito.when(userRecord.getBookIssuedList().add(bookRecord)).thenReturn(true);

        Mockito.when(userRepository.save(userRecord)).thenReturn(userRecord);
        /* Why can't I use user var instead of userRecord in above statement and also
           how to test --> user.getBookIssuedList().add(book) <-- */
        User user1 = userRepository.save(userRecord);

        ResponseEntity<String> actual = userService.issueABook(userRecord.getUserId(),bookRecord.getBookId());

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK)
                .body(bookRecord.getBookName() + " issued successfully to" +
                 " the user " + user1.getUserId());

        Assert.assertEquals(expected,actual);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUnSuccessfulWithNoBookFoundIssueBook1() {
        List<Book> issuedBookList = new ArrayList<>();
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());

        Mockito.when(bookRepository.findById(bookRecord.getBookId()))
                .thenThrow(new ResourceNotFoundException("Book does not exist with id: " + bookRecord.getBookId()));
        Optional<Book> book = bookRepository.findById(bookRecord.getBookId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUnsuccessfulWithNoUserFoundIssueBook() {
        List<Book> issuedBookList = new ArrayList<>();
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());

        Mockito.when(userRepository.findById(userRecord.getUserId()))
                .thenThrow(new ResourceNotFoundException("User does not exist with id: " + userRecord.getUserId()));

        Optional<User> user = userRepository.findById(userRecord.getUserId());
    }

    @Test
    public void testSuccessfulReturnABook() {
        List<Book> issuedBookList = new ArrayList<>();
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());
        issuedBookList.add(bookRecord);
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);

        Mockito.when(userRepository.findById(userRecord.getUserId())).thenReturn(Optional.of(userRecord));
        Mockito.when(bookRepository.findById(bookRecord.getBookId())).thenReturn(Optional.of(bookRecord));

        Optional<User> user = userRepository.findById(userRecord.getUserId());
        Optional<Book> book = bookRepository.findById(bookRecord.getBookId());

       // Mockito.when(userRecord.getBookIssuedList().contains(bookRecord)).thenReturn(true);

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK)
                .body(bookRecord.getBookName() + " returned successfully by"
                + " the user " + userRecord.getUserId());
        ResponseEntity<String> actual = userService.returnABook(userRecord.getUserId(),bookRecord.getBookId());

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testUnSuccessfulReturnABook() {
        List<Book> issuedBookList = new ArrayList<>();
        Book bookRecord = new Book(1,"BipinChandra",new Date(),new Date(),new Date());
      //  issuedBookList.add(bookRecord);
        User userRecord = new User(1,"Ashish",new Date(),new Date(),issuedBookList);

        Mockito.when(userRepository.findById(userRecord.getUserId())).thenReturn(Optional.of(userRecord));
        Mockito.when(bookRepository.findById(bookRecord.getBookId())).thenReturn(Optional.of(bookRecord));

        Optional<User> user = userRepository.findById(userRecord.getUserId());
        Optional<Book> book = bookRepository.findById(bookRecord.getBookId());

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(bookRecord.getBookName() + " not with the user " + userRecord.getUserId());

        ResponseEntity<String> actual = userService.returnABook(userRecord.getUserId(),bookRecord.getBookId());

        Assert.assertEquals(expected,actual);
    }
}