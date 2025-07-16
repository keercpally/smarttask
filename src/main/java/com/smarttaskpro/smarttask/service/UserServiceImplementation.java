package com.smarttaskpro.smarttask.service;

import com.smarttaskpro.smarttask.model.User;
import com.smarttaskpro.smarttask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//this class implements userrepo
//serviec tells spring to auto wire it
@Service //tells spring this class provides a service. Please manage it as a bean and inject it wherever needed
public class UserServiceImplementation implements  UserService{
    private final UserRepository userRepository;


    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository; // ijecting it so we can use inside service.//this is constructer injction
        // spring automatically calls this and gives you the userRepositary bean.
    }

    @Override //tells this method overrides a methid in super class or interface
    public User saveUser (User user){
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
