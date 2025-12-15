package com.example.task_manager_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_manager_backend.Entities.Assests;

public interface AssestRepo extends JpaRepository<Long, Assests> {
    
}
