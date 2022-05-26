package com.pepcus.elibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pepcus.elibrary.model.Book;
import com.pepcus.elibrary.model.User;
import com.pepcus.elibrary.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)

class UserControllerTest {

    private MockMvc mockMvc;

    /* Here we also need object mapper with which we will convert json to string and vice versa because when we do a
       post request we want to send json as a string */

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;


    List<Book> issuedBookList;
    String reg_date = "24-05-2022";
    String deact_date = "26-05-2022";

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    Date date1;
    Date date2;

    {
        try {
            date1 = formatter.parse(reg_date);
            date2 = formatter.parse(deact_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    User userRecord_1 = new User(6,"Ashish",date1,date2,issuedBookList);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> userList = new ArrayList<>(Arrays.asList(userRecord_1));
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }
}