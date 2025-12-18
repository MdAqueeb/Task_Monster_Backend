package com.example.task_manager_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Sub_Task;
import com.example.task_manager_backend.Repository.SubTaskRepo;

@Service
public class SubTaskService {
    @Autowired
    private SubTaskRepo subTaskRepo;

    public Sub_Task addSubTask(Sub_Task message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSubTask'");
    }

    public Sub_Task modifySubTask(Long id, Sub_Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifySubTask'");
    }

    public Sub_Task removeSubTask(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeSubTask'");
    }

    public List<Sub_Task> getSubTasks(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubTasks'");
    }
}
