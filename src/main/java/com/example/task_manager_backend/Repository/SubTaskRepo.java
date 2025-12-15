package com.example.task_manager_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_manager_backend.Entities.Sub_Task;

public interface SubTaskRepo extends JpaRepository<Long, Sub_Task> {
    
}
