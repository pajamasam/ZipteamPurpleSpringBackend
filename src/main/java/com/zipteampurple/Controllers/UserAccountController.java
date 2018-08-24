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
        return new ResponseEntity<>(new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value="/getUsers", method=RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        HttpStatus httpStatus = HttpStatus.OK;
        HttpHeaders httpHeaders = new HttpHeaders();
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, httpHeaders, httpStatus);
    }
 }
