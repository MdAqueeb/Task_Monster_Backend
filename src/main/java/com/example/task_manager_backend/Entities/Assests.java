package com.example.task_manager_backend.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Assests")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Assests {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assestsId;
    @Column(name = "assest", nullable = false)
    @NotBlank
    private String assests;
    @Column(name = "assest_created_at", nullable = false)
    @Builder.Default
    private LocalDateTime assestCreatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "taskId",nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
