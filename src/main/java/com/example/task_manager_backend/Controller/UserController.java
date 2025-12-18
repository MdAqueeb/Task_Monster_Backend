package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
@Validated
@Tag(name ="Users", description = "User Management API's")
public class UserController {
    
    @Autowired
    private UserService userService;
        
        // - get all user
        @GetMapping("/allusers")
        @Operation(summary = "Fetch 10 users in a page")
        public ResponseEntity<Page<User>> getAllUserDetails(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
            Page<User> users = userService.getAllUsers(page, size);
            if(users == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(users,HttpStatus.OK);
            
        }
        
        // - update password 
        @PatchMapping("/{userid}/update/password")
        @Operation(summary = "Update old password to new password")
        public ResponseEntity<User> updatePassword(@PathVariable Long userid, @RequestParam String password) {
            User updatedUser = userService.updateUserPassword(userid, password);
            if(updatedUser == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }

        // - update profile pic 
        @PatchMapping("/{userid}/profile/update")
        @Operation(summary = "Update user Profile picture")
        public ResponseEntity<User> updateProfilePic(@PathVariable Long userid, @RequestParam String profilePic) {
            User updatedUser = userService.updateUserProfilePic(userid, profilePic);
            if(updatedUser == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        // - put user
        @PutMapping("/{userid}/update")
        @Operation(summary = "Modify user details")
        public ResponseEntity<User> modifyUser(@PathVariable Long userid,@Valid @RequestBody User user) {
            User updateUser = userService.updateUser(userid, user);
            if(updateUser == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updateUser,HttpStatus.OK);
        }
        
        // - add user
        @PostMapping("/addUser")
        @Operation(summary = "Create a user")
        public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
            User usr = userService.addNewUser(user);
            if(usr == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(usr,HttpStatus.OK);
        }

        // - get user 
        @GetMapping("/{userid}")
        @Operation(summary = "Fetch specific User details")
        public ResponseEntity<User> getUserDetail(@PathVariable Long userid) {
            User usr = userService.getUser(userid);
            if(usr == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usr,HttpStatus.OK);
        }
        

}
