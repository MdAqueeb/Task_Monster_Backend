package com.example.task_manager_backend.Entities;

// import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.time.LocalTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Messages")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "message_description", nullable = false)
    @NotBlank
    private String messageDescription;
    
    @Column(name = "created_at", nullable = false)
    // @Builder.Default
    private LocalDateTime createdAt ;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "taskId",nullable = false)
    private Tasks task;

    @PrePersist
    void addDetails(){
        createdAt = LocalDateTime.now();
    }
}

