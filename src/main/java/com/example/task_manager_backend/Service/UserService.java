package com.example.task_manager_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.task_manager_backend.Repository.UsersRepo;

@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;
}
