package com.smarttaskpro.smarttask.repository;

import com.smarttaskpro.smarttask.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByUserEmail(String email);
}
