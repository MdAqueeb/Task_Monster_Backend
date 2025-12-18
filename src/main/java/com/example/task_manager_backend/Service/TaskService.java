package com.example.task_manager_backend.Service;

// import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Entities.Tasks.TaskLevel;
import com.example.task_manager_backend.Entities.Tasks.TaskStatus;
import com.example.task_manager_backend.Entities.Tasks.Trash;
import com.example.task_manager_backend.Repository.TaskRepo;
import com.example.task_manager_backend.Repository.UsersRepo;

import jakarta.persistence.EntityNotFoundException;
// import jakarta.validation.Valid;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UsersRepo userRepo;

    public Tasks addNewTask(Tasks task, Long userid) {
        System.out.println("It comes " + userid); 
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            System.out.println("DEBUG: User not found with id " + userid); 
            throw new EntityNotFoundException("User not found with id " + userid);
        }

        task.setUser(usr.get());

        return taskRepo.save(task);
    }
    // Modify below endpoint because output 500 status code 
    public Tasks getUserTask(Long taskid) {
        Optional<Tasks> tsk = taskRepo.findBytaskId(taskid, Trash.NOTTRASH);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        return tsk.get();
    }
    // Modify the below endpoint
    // reason if user not found then it returning 500, but in any case user not found or empty list have to provide
    public Page<Tasks> getAlltasks(Long userid, int page, int size) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        PageRequest pag = PageRequest.of(page, size);
        Page<Tasks> tsks = taskRepo.findTasksByUser(userid, Trash.NOTTRASH, pag);
        
        // taskRepo.findAll(pag);
        return tsks;
    }

    public Tasks updateTask(Long userid, Long taskid, Tasks task) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        Tasks tasks = tsk.get();
        if(tasks.getUser().getUserId().equals(userid)){
            tasks.setTaskTitle(task.getTaskTitle());
            tasks.setTaskDescription(task.getTaskDescription());
            tasks.setTaskLevel(task.getTaskLevel());
            tasks.setTaskStatus(task.getTaskStatus());
        }
        else{
            throw new EntityNotFoundException("You can't modified the task details " + taskid);
        }
        return taskRepo.save(tasks);
    }

    public Tasks removeTask(Long userid, Long taskid) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found with id " + userid);
        }
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        Tasks tasks = tsk.get();
        if(tasks.getUser().getUserId().equals(userid)){
            if(tasks.getTrash() == Trash.TRASH){
                taskRepo.deleteById(taskid);
            }
            else{
                throw new IllegalStateException("You can't Delete the task details directly without moving to Trash" + taskid);
            }
        }
        else{
            throw new IllegalStateException("You can't Delete the task details " + taskid);
        }
        return tasks;
    }

    public Tasks updatePiority(Long id, String entity) {
        Optional<Tasks> tsk = taskRepo.findById(id);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + id);
        }
        Tasks tasks = tsk.get();
        try{
            TaskLevel level = TaskLevel.valueOf(entity.toUpperCase());
            tasks.setTaskLevel(level);
        }  catch(IllegalStateException e){
            throw new IllegalArgumentException("Invalid priority level: " + e);
        }
        
        return taskRepo.save(tasks);
    }

    public Tasks updateStatus(Long id, String status) {
        Optional<Tasks> tsk = taskRepo.findById(id);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + id);
        }
        Tasks tasks = tsk.get();
        try{
            System.out.println("Under the print statement");
            TaskStatus level = TaskStatus.valueOf(status.toUpperCase());
            System.out.println(level+" These is level");
            tasks.setTaskStatus(level);
        } catch(IllegalStateException e){
            throw new IllegalArgumentException("Invalid Status level: " + status);
        }
        
        return taskRepo.save(tasks);
    }

    public Tasks taskTrash(Long taskid) {
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        Tasks tasks = tsk.get();
        if(tasks.getTrash() == Trash.TRASH){
            throw new IllegalArgumentException("Task Already in Trash" + taskid);
        }
        tasks.setTrash(Trash.TRASH);
        return taskRepo.save(tasks);
    }

    public Tasks taskRestore(Long taskid) {
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found with id " + taskid);
        }
        Tasks tasks = tsk.get();
        if(tasks.getTrash() == Trash.NOTTRASH){
            throw new IllegalArgumentException("Task Already restore" + taskid);
        }
        tasks.setTrash(Trash.NOTTRASH);
        return taskRepo.save(tasks);
    }

}
