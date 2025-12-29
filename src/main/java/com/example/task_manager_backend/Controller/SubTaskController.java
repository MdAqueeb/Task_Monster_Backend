package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.task_manager_backend.Entities.Messages;
import com.example.task_manager_backend.Entities.Sub_Task;
import com.example.task_manager_backend.Service.SubTaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Sub Tasks", description = "Sub Task all API's")
@RequestMapping("/subtask")
@Validated
public class SubTaskController {
    @Autowired
    private SubTaskService subTaskService;

    // sub-task:
    //     - add sub task 
    @PostMapping("/create/{userid}/{taskid}")
    @Operation(summary = "Create new Sub task")
    public ResponseEntity<Sub_Task> addSubTask(@Valid @RequestBody Sub_Task subtask, @PathVariable Long userid, @PathVariable Long taskid) {
        Sub_Task tsk = subTaskService.addSubTask(subtask, userid, taskid);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
        
    }
    
    //     - update sub-task 
    @PutMapping("/update/{userid}/{subtaskid}")
    @Operation(summary = "Modify specific sub task details")
    public ResponseEntity<Sub_Task> updateSubTask(@PathVariable Long userid, @PathVariable Long subtaskid, @Valid @RequestBody Sub_Task task) {
        Sub_Task tsk = subTaskService.modifySubTask(userid, subtaskid, task);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - delete sub-task 
    @DeleteMapping("/remove/{userid}/{subtaskid}")
    @Operation(summary = "Remove specific Sub task")
    public ResponseEntity<Sub_Task> deleteSubTask(@PathVariable Long userid, @PathVariable Long subtaskid) {
        Sub_Task tsk = subTaskService.removeSubTask(userid, subtaskid);
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - get all sub-task
    
    @GetMapping("/{taskid}")
    @Operation(summary = "Fetch all Sub task of particular task")
    public ResponseEntity<List<Sub_Task>> getAllSubTasks(@PathVariable Long taskid) {
        List<Sub_Task> tsk = subTaskService.getSubTasks(taskid);
        
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    
}
