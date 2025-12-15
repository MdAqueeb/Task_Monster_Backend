package com.example.task_manager_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Repository.SubTaskRepo;

@Service
public class SubTaskService {
    @Autowired
    private SubTaskRepo subTaskRepo;
}
