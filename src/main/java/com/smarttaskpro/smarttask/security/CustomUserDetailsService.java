package com.smarttaskpro.smarttask.security;

import com.smarttaskpro.smarttask.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    public CustomUserDetailsService (UserService userService){
        this.userService=userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        com.smarttaskpro.smarttask.model.User user= userService.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with email: "+email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
