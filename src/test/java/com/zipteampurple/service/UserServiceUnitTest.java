//package com.zipteampurple.service;
//
//import com.zipteampurple.Entity.User;
//import com.zipteampurple.Repository.UserRepository;
//import com.zipteampurple.Service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class UserServiceUnitTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void addUserTest(){
//
//        //Create User
//        User aMockUser = new User();
//        aMockUser.setFirstName("Madonna");
//        aMockUser.setLastName("Dons");
//        aMockUser.setPassword("password");
//        aMockUser.setUsername("mdons");
//        aMockUser.setEmail("mdons@gmail.com");
//
//        Mockito.when(userRepository.save(any(User.class))).thenReturn(aMockUser);
//
//        //Save the User
//        User newUser = userService.save(null);
//
//        //Verify the save
//        assertEquals("Madonna", newUser.getFirstName());
//
//
//    }
//
//
//
//
//}
