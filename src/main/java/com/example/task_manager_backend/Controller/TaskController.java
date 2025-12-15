package com.example.task_manager_backend.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Service.TaskService;

@RestController
public class TaskController {
    private TaskService taskService;
}
