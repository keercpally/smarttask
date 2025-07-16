package com.smarttaskpro.smarttask.repository;

import com.smarttaskpro.smarttask.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
