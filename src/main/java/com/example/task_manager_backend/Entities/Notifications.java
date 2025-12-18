package com.example.task_manager_backend.Entities;

// import java.time.LocalDate;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notification")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    @Column(name = "subject", nullable = false)
    @NotBlank
    private String notificationSubject;
    @Column(name = "message", nullable = false)
    @NotBlank
    private String notificationMessage;
    @Builder.Default
    @Column(name="notification_Created_At", nullable = false)
    private LocalDateTime notificationCreatedAt= LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private NotificationStatus notificationStatus=NotificationStatus.UNREAD;


    enum NotificationStatus{
        READ, UNREAD
    }

    @ManyToOne
    @JoinColumn(name = "taskId",nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
