package com.example.task_manager_backend.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SubTask")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Sub_Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subTaskId;

    @Column(name = "sub_task_title", nullable = false)
    @NotBlank
    private String subTaskTitle;

    @Column(name = "sub_task_description", nullable = false)
    @NotBlank
    private String subTaskDescription;

    // @Builder.Default
    @Column(name="sub_task_Created_At", nullable = false)
    private LocalDateTime subTaskCreatedAt;

    @Enumerated(EnumType.STRING)
    // @Builder.Default
    private SubTaskStatus subTaskStatus;

    enum SubTaskStatus{
        INPROGRESS, COMPLETED
    }

    @ManyToOne
    @JoinColumn(name = "taskId",nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @PrePersist
    void defaulValues(){
        subTaskCreatedAt = LocalDateTime.now();
        subTaskStatus = SubTaskStatus.INPROGRESS;
    }
}
