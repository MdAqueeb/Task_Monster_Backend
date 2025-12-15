package com.example.task_manager_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task_manager_backend.Entities.Sub_Task;

@Repository
public interface SubTaskRepo extends JpaRepository<Sub_Task, Long> {
    
}
