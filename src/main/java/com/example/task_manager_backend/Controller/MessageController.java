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

import com.example.task_manager_backend.Entities.Messages;
// import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Message Manager", description = "Message Manager API's")
@RequestMapping("/Messages")
@Validated
public class MessageController {
    @Autowired
    private MessageService messageService;

    // - Messages: 
    //     - add message 
    @PostMapping("/{userid}/{taskid}/createMessage")
    @Operation(summary = "Create new Message")
    public ResponseEntity<Messages> addMessage(@PathVariable Long userid, @PathVariable Long taskid, @Valid @RequestBody Messages message) {
        Messages msg = messageService.addNewMessage(userid, taskid, message);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
    
    //     - update message 
    @PutMapping("/{userid}/{messageid}/modifyMessage")
    @Operation(summary = "Modify specific Message details")
    public ResponseEntity<Messages> messageUpdate(@PathVariable Long userid, @PathVariable Long messageid,@Valid @RequestBody Messages task) {
        System.out.println("Yes modify controller method");
        Messages tsk = messageService.modifyMessage(userid, messageid, task);
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - delete message 
    @DeleteMapping("/{userid}/{messageid}")
    @Operation(summary = "Remove specific Message")
    public ResponseEntity<Messages> deleteMessage(@PathVariable Long userid, @PathVariable Long messageid) {
        Messages tsk = messageService.removeMessage(userid, messageid);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - get message
    
    @GetMapping("/{taskid}")
    @Operation(summary = "Fetch particular Message")
    public ResponseEntity<List<Messages>> getMessage(@PathVariable Long taskid) {
        List<Messages> tsk = messageService.getMessage(taskid);
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
}
