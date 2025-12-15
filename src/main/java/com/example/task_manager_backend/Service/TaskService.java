package com.example.task_manager_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Repository.TaskRepo;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepo taskRepo;

    public Tasks addNewTask(Tasks task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewTask'");
    }

    public Tasks getUserTask(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserTask'");
    }

    public List<Tasks> getAlltasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlltasks'");
    }

    public Tasks updateTask(Long id, Tasks task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

    public Tasks removeTask(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTask'");
    }

    public Tasks updatePiority(String id, String entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePiority'");
    }

    public Tasks updateStatus(String id, String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
}
