package com.pepcus.elibrary.service;

import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.BookShelf;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.BookRepository;
import com.pepcus.elibrary.repository.BookShelfRepository;
import com.pepcus.elibrary.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    // CTRL+SHIFT+T create a test for class
    // CTRL+Q suggest method parameters
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;

    private UserService userService;

    @Mock
    BookShelfRepository bookShelfRepository;
    @InjectMocks
    private BookShelfService bookShelfService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository,bookRepository);
    }

    // Setting up User fields for creating a demo user
    List<Book> issuedBookList = new ArrayList<>();
    Date date1 = new Date();
    Date date2 = new Date();
    Date date3 = new Date();
    User userRecord_2 = new User(2,"Sooraj",date1,date2,issuedBookList);

    // Setting up Book fields for creating a dummy book
    Book bookRecord = new Book(1,"BipinChandra",date1,date2,date3);
    List<Book> bookListRecord = new ArrayList<>(Arrays.asList(bookRecord));

    BookShelf bookShelfRecord = new BookShelf(1,"History",bookListRecord);

    @Test
    public void testSaveUser() {

//        List<Book> issuedBookList = null;
//        Date date1 = new Date();
//        Date date2 = new Date();
//
//        User userRecord_2 = new User(2,"Sooraj",date1,date2,issuedBookList);

        Mockito.when(userRepository.save(userRecord_2)).thenReturn(userRecord_2);
        User user = userService.saveUser(userRecord_2);
        //  Assert.assertEquals(userRecord_2,user);

        Assert.assertEquals(userRecord_2.getUserId(),user.getUserId());
    }

    @Test
    public void testDeRegisterUser() {
        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body("User with id-" +
                userRecord_2.getUserId() + " deregistered " + "successfully");

        userService.saveUser(userRecord_2);

        final ResponseEntity<String> actual
                = userService.deRegisterUser(userRecord_2, userRecord_2.getUserId());
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void issueABook() {

        userService.saveUser(userRecord_2);
        bookShelfService.saveBookShelf(bookShelfRecord);


        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(bookRecord.getBookName()
                + " issued successfully to"
                + " the user " + userRecord_2.getUserId());
        final ResponseEntity<String> actual =
                userService.issueABook(userRecord_2.getUserId(),bookListRecord.get(0).getBookId());
        Assert.assertEquals(expected,actual);


    }

    @Test
    public void returnABook() {
    }
}