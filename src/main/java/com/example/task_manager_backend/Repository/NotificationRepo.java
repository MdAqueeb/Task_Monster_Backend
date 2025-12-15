package com.example.task_manager_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_manager_backend.Entities.Notifications;

public interface NotificationRepo extends JpaRepository<Long, Notifications> {
    
}
