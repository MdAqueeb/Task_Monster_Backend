package com.example.task_manager_backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TeamMember")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamMember;

    @ManyToOne
    @JoinColumn(name = "taskId",nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    
}
