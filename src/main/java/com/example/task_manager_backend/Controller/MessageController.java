package com.example.task_manager_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.Messages;
import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Message Manager", description = "Message Manager API's")
public class MessageController {
    @Autowired
    private MessageService messageService;

    // - Messages: 
    //     - add message 
    @PostMapping("/createMessage")
    @Operation(summary = "Create new Message")
    public ResponseEntity<Messages> addMessage(@RequestBody Messages message) {
        Messages msg = messageService.addNewMessage(message);
        if(msg == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
        
    }
    
    //     - update message 
    @PutMapping("/{id}")
    @Operation(summary = "Modify specific Message details")
    public ResponseEntity<Messages> messageUpdate(@PathVariable Long id, @RequestBody Messages task) {
        Messages tsk = messageService.modifyMessage(id, task);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
    //     - delete message 
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove specific Message")
    public ResponseEntity<Messages> deleteMessage(@PathVariable Long id) {
        Messages tsk = messageService.removeMessage(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - get message
    
    @GetMapping("/{id}")
    @Operation(summary = "Fetch particular Message")
    public ResponseEntity<Messages> getMessage(@PathVariable Long id) {
        Messages tsk = messageService.getMessage(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
}
