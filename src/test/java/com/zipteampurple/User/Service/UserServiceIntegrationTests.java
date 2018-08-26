package com.zipteampurple.User.Service;

import com.zipteampurple.Entity.User;
import com.zipteampurple.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){
        //Creating a new User
        User user = new User();
        user.setFirstName("Madonna");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("mdons");
        user.setEmail("mdons@gmail.com");

        //Test Adding the User
        User newUser = userService.save(user);

        //Verify the Addition
        assertNotNull(newUser);
        assertNotNull(newUser.getId());
        assertEquals("Madonna", newUser.getFirstName());
        assertEquals("Dons", newUser.getLastName());
        assertEquals("mdons", newUser.getUsername());
        assertEquals("mdons@gmail.com", newUser.getEmail());

        //The password word will be hashed before it is saved into the database
        assertNotEquals("password", newUser.getPassword());
    }

    @Test
    public void rejectSaveIfUsernameExists(){
        //Creating a new User
        User user = new User();
        user.setFirstName("Madonna");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("mdons");
        user.setEmail("mdons@gmail.com");

        //Saves the users to the database
        this.userService.save(user);

        //User Tries to register with the same username
        User user2 = new User();
        user2.setFirstName("Mick");
        user2.setLastName("Dons");
        user2.setPassword("password");
        user2.setUsername("mdons");
        user2.setEmail("ndons@gmail.com");

        User newUser = this.userService.save(user2);

        assertNull(newUser);
    }

    @Test
    public void rejectSaveIfEmailExists(){
        //Creating a new User
        User user = new User();
        user.setFirstName("Madonna");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("mdons");
        user.setEmail("mdons@gmail.com");

        //Saves the users to the database
        this.userService.save(user);

        //User Tries to register with the same username
        User user2 = new User();
        user2.setFirstName("Mick");
        user2.setLastName("Dons");
        user2.setPassword("password");
        user2.setUsername("ndons");
        user2.setEmail("mdons@gmail.com");

        User newUser = this.userService.save(user2);

        assertNull(newUser);
    }
}
