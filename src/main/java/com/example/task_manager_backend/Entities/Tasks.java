package com.example.task_manager_backend.Entities;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

// import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name = "task_title", nullable = false)
    @NotBlank
    private String taskTitle;
    @Column(name = "task_description", nullable = false)
    @NotBlank
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "task_level", nullable = false)
    private TaskLevel taskLevel = TaskLevel.LOW;

    @Enumerated(EnumType.STRING)
    // @Builder.Default
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus ;

    @Enumerated(EnumType.STRING)
    // @Builder.Default
    @Column(name = "task_trash", nullable = false)
    private Trash trash;
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    // @Builder.Default
    private LocalDateTime createdAt;
    @Column(name = "due_at", columnDefinition = "DATETIME")
    private LocalDateTime dueAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public enum TaskLevel{
        LOW, MEDIUM, HIGH
    }

    public enum TaskStatus{
        TODO, INPROGRESS, COMPLETED, OVERDUE
    }

    public enum Trash {
        TRASH, NOTTRASH
    }

    // messages 
    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messages> taskMessage;
    //subtask
    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sub_Task> taskSubTask;
    //assests 
    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assests> taskAssests;
    // notification 
    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private List<Notifications> taskNotification;
    // team member
    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> taskTeamMembers;


    @PrePersist
    void oncreate(){
        createdAt = LocalDateTime.now();
        trash = Trash.NOTTRASH;
        taskStatus = TaskStatus.TODO;
    }
}
