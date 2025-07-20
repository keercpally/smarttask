package com.smarttaskpro.smarttask.controller;
import com.smarttaskpro.smarttask.model.Project;
import com.smarttaskpro.smarttask.model.User;
import com.smarttaskpro.smarttask.service.ProjectService;
import com.smarttaskpro.smarttask.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(projectService.getAllProjectsByUser(user));
    }

    @PostMapping

    public ResponseEntity<Project> createProject(@RequestBody Project project,@AuthenticationPrincipal UserDetails userdetails) {

        User user = userService.findByEmail(userdetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Fetched user: " + user);
        System.out.println("User ID: " + user.getId());

        project.setUser(user);
        return ResponseEntity.ok(projectService.createProject(project));
    }
}


