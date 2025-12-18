package com.example.task_manager_backend.Controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
@Validated
public class TaskController {
    @Autowired
    private TaskService taskService;

    //     - create task 
    @PostMapping("/{userid}/createtask")
    @Operation(summary = "Create new Task")
    public ResponseEntity<Tasks> addTask(@Valid @RequestBody Tasks task, @PathVariable Long userid) {
        System.out.println("Endpoint fetch" + userid); 
        Tasks tsk = taskService.addNewTask(task, userid);
        return new ResponseEntity<>(tsk,HttpStatus.CREATED);
    }
    
    //     - get task 
    @GetMapping("/{taskid}")
    @Operation(summary = "Fetch particular id task")
    public ResponseEntity<Tasks> getTask(@PathVariable Long taskid) {
        Tasks tsk = taskService.getUserTask(taskid);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    
    //     - get all task(in pagination manner)
    @GetMapping("/{userid}/allTasks")
    @Operation(summary = "Fetch all task in specific size")
    public ResponseEntity<Page<Tasks>> getAllTask(@PathVariable Long userid, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Tasks> tsk = taskService.getAlltasks(userid, page, size);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    
    //     - update task 
    @PutMapping("/{userid}/{taskid}")
    @Operation(summary = "Modify task details")
    public ResponseEntity<Tasks> taskUpdate(@PathVariable Long userid,@PathVariable Long taskid,@Valid @RequestBody Tasks task) {
        Tasks tsk = taskService.updateTask(userid,taskid, task);
        
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - delete task 
    @DeleteMapping("/{userid}/{taskid}")
    @Operation(summary = "Remove specific task")
    public ResponseEntity<Tasks> deleteTask(@PathVariable Long userid, @PathVariable Long taskid) {
        Tasks tsk = taskService.removeTask(userid, taskid);
        
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - update piority level 
    @PatchMapping("/{taskid}/update/piority")
    @Operation(summary = "Update specific task piority")
    public ResponseEntity<Tasks> updatePiority(@PathVariable Long taskid, @RequestParam String entity) {
        Tasks tsk = taskService.updatePiority(taskid, entity);
        
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - update task stage 
    @PatchMapping("/{taskid}/update/taskStatus")
    @Operation(summary = "Update specific task Status")
    public ResponseEntity<Tasks> updateTaskStatus(@PathVariable Long taskid, @RequestParam String status) {
        Tasks tsk = taskService.updateStatus(taskid, status);
        
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }

    // add task to trash 
    @PatchMapping("/{taskid}/trash")
    @Operation(summary = "Task moving to trash")
    public ResponseEntity<Tasks> trashTask(@PathVariable Long taskid) {
        Tasks tsk = taskService.taskTrash(taskid);
        
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    // restore task 
    @PatchMapping("/{taskid}/restore")
    @Operation(summary = "Task is restore")
    public ResponseEntity<Tasks> restoreTask(@PathVariable Long taskid) {
        Tasks tsk = taskService.taskRestore(taskid);
      
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
}
