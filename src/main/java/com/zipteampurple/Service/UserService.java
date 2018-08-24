package com.zipteampurple.Service;

import com.zipteampurple.Entity.User;
import com.zipteampurple.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ChannelService channelService;

    public User save(User user) {
        User createdUser = null;

        boolean isEmailUnique    = userRepository.existsByEmail(user.getEmail());
        boolean isUsernameUnique = this.userRepository.existsByUsername(user.getUsername());

        boolean isUnique = !isEmailUnique && !isUsernameUnique;

        if(isUnique) {
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            createdUser = userRepository.save(user);
            this.channelService.addUserToDefault(createdUser);
        }
        return createdUser;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User find(String userName) { return userRepository.findOneByUsername(userName); }

    public User find(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? userRepository.findOneByUsername(auth.getName()) : null;
    }

}

