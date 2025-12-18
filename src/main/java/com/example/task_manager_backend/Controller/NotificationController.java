package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.Notifications;
import com.example.task_manager_backend.Entities.TeamMember;
import com.example.task_manager_backend.Service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@Tag(name = "Notification", description = "Notification All API's")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Notifications:
    //     - get all notification 
    //     - get notification 
    //     - update notification status 
    //     - delete all notifications 
    //     - delete notification

    @GetMapping("/{userid}/notifications")
    @Operation(summary = "get All notification")
    public ResponseEntity<List<Notifications>> getAllNotifications(@PathVariable Long userid) {
        List<Notifications> notification = notificationService.getAllNotifications(userid);
        if(notification == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(notification,HttpStatus.OK);
    }

    @GetMapping("/{userid}/notification/{notificationid}")
    public ResponseEntity<Notifications> getNotification(@PathVariable Long userid, @PathVariable Long notificationid) {
        Notifications notification = notificationService.getAllNotifications(userid, notificationid);
        if(notification == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(notification,HttpStatus.OK);
    }
    
    @PostMapping("/{userid}/addNotification/{anotherid}")
    public ResponseEntity<Notifications> createNotification(@PathVariable Long userid, @PathVariable Long anotherid,@RequestBody Notifications notification) {
        Notifications notifications = notificationService.addNotification(userid, anotherid, notification );
        if(notification == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(notifications,HttpStatus.OK);
    }
    
    // @DeleteMapping("/{id}")
    // @Operation(summary = "Remove specific member in task")
    // public ResponseEntity<TeamMember> deleteMember(@PathVariable Long id) {
    //     TeamMember tsk = teamMemberService.removeTeamMember(id);
    //     if(tsk == null){
    //         return new ResponseEntity<>(HttpStatus.CONFLICT);
    //     }
    //     return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    // }
}
