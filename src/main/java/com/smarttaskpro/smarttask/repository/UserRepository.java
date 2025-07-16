package com.smarttaskpro.smarttask.repository;

import com.smarttaskpro.smarttask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//define java interface . This will act as Data Access Layer (talks to database),spring boot creates the implementation at runtime.
//extends JpaRepository<User, Long> - This repo will manage User entities and primary key is type long
//User is entity class ID column type long, now we can use userrepo.findall(),findbyId(id),save(user),deletebyID(id)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // will be using this for login
     // Optional-this result might exist or not so wrapped in optional
    //findby... tells spring to query using field that follows
    //Email... must match the field name in user table.
     //generates select * from user where email=?
}
