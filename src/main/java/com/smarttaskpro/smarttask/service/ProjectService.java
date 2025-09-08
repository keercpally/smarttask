package com.smarttaskpro.smarttask.service;

import com.smarttaskpro.smarttask.model.Project;
import com.smarttaskpro.smarttask.model.User;
import com.smarttaskpro.smarttask.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public List<Project> getAllProjectsByUser(User user){
        return projectRepository.findByUserEmail(user.getEmail());
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(Long id, Project updatedProject, User user) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id, User user) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }


    public Project getProjectByIdAndUser(Long id, User user) {
        return projectRepository.findById(id)
                .filter(project -> project.getUser().getEmail().equals(user.getEmail()))
                .orElseThrow(() -> new RuntimeException("Project not found or does not belong to the user"));
    }
}
