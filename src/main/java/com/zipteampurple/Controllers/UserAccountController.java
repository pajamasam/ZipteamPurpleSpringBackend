package com.zipteampurple.Controllers;

import com.zipteampurple.Entity.User;
import com.zipteampurple.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserAccountController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User newUser){
        HttpStatus httpStatus = null;
        User createdUser = this.userService.save(newUser);
        httpStatus = createdUser != null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<>(createdUser, new HttpHeaders(), httpStatus);
    }

//    @RequestMapping(value="/login", method=RequestMethod.POST)
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
//        HttpHeaders httpHeaders = new HttpHeaders();
//        User foundUser = userService.find(user.getUserName());
//        if (foundUser != null) {
//            if (foundUser.getPassword().equals(user.getPassword())) httpStatus = HttpStatus.ACCEPTED;
//        }
//        return new ResponseEntity<>(foundUser, httpHeaders, httpStatus);
//    }

    @RequestMapping(value="/getUsers", method=RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        HttpStatus httpStatus = HttpStatus.OK;
        HttpHeaders httpHeaders = new HttpHeaders();
        List<User> users = userService.getAllUsers();

        Set<String> set = new HashSet<>();

        set.toArray(new String[set.size()]);

//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        String usersString = null;
//
//        try {
//            usersString = objectMapper.writeValueAsString(users);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return new ResponseEntity<>(users, httpHeaders, httpStatus);
    }
 }
