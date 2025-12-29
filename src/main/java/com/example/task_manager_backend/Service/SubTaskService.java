package com.example.task_manager_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Sub_Task;
import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Repository.SubTaskRepo;
import com.example.task_manager_backend.Repository.TaskRepo;
import com.example.task_manager_backend.Repository.UsersRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SubTaskService {
    @Autowired
    private SubTaskRepo subTaskRepo;

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    public Sub_Task addSubTask(Sub_Task subtask, Long userid, Long taskid) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        subtask.setUser(usr.get());
        subtask.setTask(tsk.get());
        return subTaskRepo.save(subtask);
    }

    @Transactional
    public Sub_Task modifySubTask(Long userid, Long subtaskid, Sub_Task task) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        Optional<Sub_Task> tsk = subTaskRepo.findById(subtaskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + subtaskid);
        }

        Sub_Task subtask = tsk.get();

        if(userid.equals(subtask.getUser().getUserId())){
            subtask.setSubTaskTitle(task.getSubTaskTitle());
            subtask.setSubTaskDescription(task.getSubTaskDescription());
            subtask.setSubTaskStatus(task.getSubTaskStatus());
            return subTaskRepo.save(subtask);
        }
        else{
            throw new IllegalStateException("You can't modify the subtask");
        }
    }

    @Transactional
    public Sub_Task removeSubTask(Long userid, Long subtaskid) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        Optional<Sub_Task> tsk = subTaskRepo.findById(subtaskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Sub task not found with id " + subtaskid);
        }

        Sub_Task subtask = tsk.get();

        if(userid.equals(subtask.getUser().getUserId())){
            subTaskRepo.delete(subtask);
            return subtask;
        }
        else{
            throw new IllegalStateException("You can't modify the subtask");
        }
    }

    public List<Sub_Task> getSubTasks(Long taskid) {
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }

        return subTaskRepo.findByTaskId(taskid);
    }
}
