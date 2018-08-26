//package com.zipteampurple.controller;
//
//
//import com.zipteampurple.Controllers.UserAccountController;
//import com.zipteampurple.Entity.User;
//import com.zipteampurple.Service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserAccountController.class)
//public class UserControllerUnitTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @InjectMocks
//    private UserAccountController userAccountController;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void addUserSuccessfulTest() throws Exception {
//
//        //Creating a User
//        User user = new User();
//        user.setFirstName("Madonna");
//        user.setLastName("Dons");
//        user.setPassword("password");
//        user.setUsername("mdons");
//        user.setEmail("mdons@gmail.com");
//
//        when(userService.save(any(User.class))).thenReturn(user);
//
//        mockMvc.perform(post("/register", user)).andExpect(status().isCreated()).andReturn();
//
//
//    }
//
//}


