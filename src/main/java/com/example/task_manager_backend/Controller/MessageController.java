package com.example.task_manager_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Service.MessageService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
}
