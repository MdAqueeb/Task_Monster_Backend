package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@Tag(name = "Task Management", description="Task Manager API's")
@RequestMapping("/Task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    //     - create task 
    @PostMapping("/createtask")
    @Operation(summary = "Create new Task")
    public ResponseEntity<Tasks> addTask(@RequestBody Tasks task) {
        Tasks tsk = taskService.addNewTask(task);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
        
    }
    
    //     - get task 
    @GetMapping("/{id}")
    @Operation(summary = "Fetch particular id task")
    public ResponseEntity<Tasks> getTask(@PathVariable Long id) {
        Tasks tsk = taskService.getUserTask(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    
    //     - get all task(in pagination manner)
    @GetMapping("/allTasks")
    @Operation(summary = "Fetch all task in specific size")
    public ResponseEntity<List<Tasks>> getAllTask() {
        List<Tasks> tsk = taskService.getAlltasks();
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    
    //     - update task 
    @PutMapping("/{id}")
    @Operation(summary = "Modify task details")
    public ResponseEntity<Tasks> taskUpdate(@PathVariable Long id, @RequestBody Tasks task) {
        Tasks tsk = taskService.updateTask(id, task);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - delete task 
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove specific task")
    public ResponseEntity<Tasks> deleteTask(@PathVariable Long id) {
        Tasks tsk = taskService.removeTask(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - update piority level 
    @PatchMapping("/{id}/update/piority")
    @Operation(summary = "Update specific task piority")
    public ResponseEntity<Tasks> updatePiority(@PathVariable String id, @RequestParam String entity) {
        Tasks tsk = taskService.updatePiority(id, entity);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - update task stage 
    @PatchMapping("/{id}/update/taskStatus")
    @Operation(summary = "Update specific task piority")
    public ResponseEntity<Tasks> updateTaskStatus(@PathVariable String id, @RequestParam String status) {
        Tasks tsk = taskService.updateStatus(id, status);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }

    // add task to trash 

    // restore task 
}
