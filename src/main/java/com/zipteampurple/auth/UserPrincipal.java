package com.zipteampurple.auth;

import com.zipteampurple.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {

    private User user;
    private List<AuthGroup> authGroups;

    public UserPrincipal(User user, List<AuthGroup> authGroups){
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(null == authGroups){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthority = new HashSet<>();
        authGroups.forEach( group -> {
            grantedAuthority.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}