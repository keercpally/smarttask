package com.smarttaskpro.smarttask.service;

import com.smarttaskpro.smarttask.model.Project;
import com.smarttaskpro.smarttask.model.Task;
import com.smarttaskpro.smarttask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getTasksByProject(Project project){
        return taskRepository.findByProjectId(project.getId());
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }


    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Long id,Task updatedTask){
        Task existingTask= taskRepository.findById(id).orElseThrow(() ->new RuntimeException("Task not found"));
        existingTask.setName(updatedTask.getName());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        return taskRepository.save(existingTask);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
