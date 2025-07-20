package com.smarttaskpro.smarttask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assigned_to;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
}
