package com.example.task_manager_backend.Entities;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Builder.Default
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus = TaskStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "task_trash", nullable = false)
    private Trash trash = Trash.NOTTRASH;
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "due_at", columnDefinition = "DATETIME")
    private LocalDateTime dueAt;

    enum TaskLevel{
        LOW, MEDIUM, HIGH
    }

    enum TaskStatus{
        TODO, INPROGRESS, COMPLETED, OVERDUE
    }

    enum Trash {
        TRASH, NOTTRASH
    }

    // messages 
    @OneToMany(mappedBy = "task")
    private List<Messages> taskMessage;
    //subtask
    @OneToMany(mappedBy = "task")
    private List<Sub_Task> taskSubTask;
    //assests 
    @OneToMany(mappedBy = "task")
    private List<Assests> taskAssests;
    // notification 
    @OneToMany(mappedBy = "task")
    private List<Notifications> taskNotification;
    // team member
    @OneToMany(mappedBy = "task")
    private List<TeamMember> taskTeamMembers;
}
