package com.smarttaskpro.smarttask.controller;

import com.smarttaskpro.smarttask.dto.LoginRequest;
import com.smarttaskpro.smarttask.dto.LoginResponse;
import com.smarttaskpro.smarttask.dto.RegisterRequest;
import com.smarttaskpro.smarttask.model.User;
import com.smarttaskpro.smarttask.security.JwtUtil;
import com.smarttaskpro.smarttask.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request){
        User user=new User(null,request.getName(),request.getEmail(),request.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            String token=jwtUtil.generateToken(request.getEmail());
            return new LoginResponse(token);
        }catch (AuthenticationException e){
            throw new RuntimeException("Invalid credentials");
        }

    }





}
