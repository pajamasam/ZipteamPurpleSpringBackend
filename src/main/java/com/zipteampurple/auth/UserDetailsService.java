package com.zipteampurple.auth;

import com.zipteampurple.Entity.User;
import com.zipteampurple.Repository.UserRepository;
import com.zipteampurple.auth.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    private final AuthGroupRepository authGroupRepository;


    public UserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository){
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if( user == null ){
            throw new UsernameNotFoundException("cannot find username: " + username);
        }

        List<AuthGroup> authGroups =  this.authGroupRepository.findByUsername(username);
        return new UserPrincipal(user, authGroups);
    }
}
