package com.smarttaskpro.smarttask.controller;

import com.smarttaskpro.smarttask.model.Project;
import com.smarttaskpro.smarttask.model.Task;
import com.smarttaskpro.smarttask.repository.ProjectRepository;
import com.smarttaskpro.smarttask.service.ProjectService;
import com.smarttaskpro.smarttask.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService){
        this.taskService=taskService;
        this.projectService=projectService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId){
        Project project = projectService.getProjectById(projectId);
        List<Task> tasks = taskService.getTasksByProject(project);
        return ResponseEntity.ok(tasks);
        }
        @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
            Project project = projectService.getProjectById(task.getProject().getId());
            task.setProject(project);
        Task createdTask =taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
        }

        @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task task){
        Task updatedTask = taskService.updateTask(id,task);
        return ResponseEntity.ok(updatedTask);
        }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
        }
    }


