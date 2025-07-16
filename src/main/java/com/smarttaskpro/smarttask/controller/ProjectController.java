package com.smarttaskpro.smarttask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @GetMapping
    public ResponseEntity<String> getAllProjects() {
        return ResponseEntity.ok("Projects accessed successfully with JWT!");
    }
}
