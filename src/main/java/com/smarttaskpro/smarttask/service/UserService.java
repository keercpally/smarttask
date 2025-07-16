package com.smarttaskpro.smarttask.service;

import com.smarttaskpro.smarttask.model.User;

import java.util.Optional;
//this class says , u needd to find user/save

public interface UserService {

    User saveUser(User user);
    Optional<User> findByEmail(String email);

}
