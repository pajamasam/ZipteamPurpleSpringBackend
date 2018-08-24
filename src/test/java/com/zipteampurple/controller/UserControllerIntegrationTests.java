package com.zipteampurple.controller;


import com.zipteampurple.Controllers.UserAccountController;
import com.zipteampurple.Entity.User;
import com.zipteampurple.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTests {

    @Autowired
    UserAccountController userAccountController;

    @Test
    public void addUserSuccessfulTest(){

        //Creating a User
        User user = new User();
        user.setFirstName("Jimmy");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("jdons");
        user.setEmail("jdons@gmail.com");

        //Post our User form Bean to the Controller; Check the outcome
        ResponseEntity responseEntity = userAccountController.registerUser(user);

        //Assert that that
        assertEquals(responseEntity.getStatusCode(),HttpStatus.CREATED);
        assertNull(responseEntity.getBody());

    }

    @Test
    public void addUserConflict(){
        //Creating a User
        User user = new User();
        user.setFirstName("Madonna");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("mdons");
        user.setEmail("mdons@gmail.com");

        //Adds the user to the Database
        userAccountController.registerUser(user);

        //Then when you try and add the second person to the database you will get a Conflict
        ResponseEntity responseEntity = userAccountController.registerUser(user);

        //Assert that that
        assertEquals(responseEntity.getStatusCode(),HttpStatus.CONFLICT);
        assertNull(responseEntity.getBody());
    }
}
