package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
@Tag(name ="Users", description = "User Management API's")
public class UserController {
    
    @Autowired
    private UserService userService;
        
        // - get all user
        @GetMapping
        @Operation(summary = "Fetch Specific number of users")
        public ResponseEntity<List<User>> getAllUserDetails() {
            List<User> users = userService.getAllUsers();
            if(users == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(users,HttpStatus.OK);
            
        }
        
        // - update password 
        @PatchMapping("/{id}/update/password")
        @Operation(summary = "Update old password to new password")
        public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestParam String password) {
            User updatedUser = userService.updateUserPassword(id, password);
            if(updatedUser == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }

        // - update profile pic 
        @PatchMapping
        @Operation(summary = "Update user Profile picture")
        public ResponseEntity<User> updateProfilePic(@PathVariable Long id, @RequestParam String profilePic) {
            User updatedUser = userService.updateUserProfilePic(id, profilePic);
            if(updatedUser == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        // - put user
        @PutMapping("/{id}")
        @Operation(summary = "Modify user details")
        public ResponseEntity<User> modifyUser(@PathVariable Long id, @RequestBody User user) {
            User updateUser = userService.updateUser(id, user);
            if(updateUser == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(updateUser,HttpStatus.OK);
        }
        
        // - add user
        @PostMapping("/addUser")
        @Operation(summary = "Create a user")
        public ResponseEntity<User> addUser(@RequestBody User user) {
            User usr = userService.addNewUser(user);
            if(usr == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(usr,HttpStatus.OK);
        }

        // - get user 
        @GetMapping("/{id}")
        @Operation(summary = "Fetch specific User details")
        public ResponseEntity<User> getUserDetail(@PathVariable Long id) {
            User usr = userService.getUser(id);
            if(usr == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(usr,HttpStatus.OK);
        }
        

}
