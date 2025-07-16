package com.smarttaskpro.smarttask.repository;

import com.smarttaskpro.smarttask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {


}
