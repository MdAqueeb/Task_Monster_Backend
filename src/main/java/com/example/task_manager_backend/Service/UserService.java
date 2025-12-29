package com.example.task_manager_backend.Service;

// import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.example.task_manager_backend.Controller.List;
// import java.util.List;
import java.util.Optional;

import javax.security.auth.login.FailedLoginException;

import com.example.task_manager_backend.DTO.LoginDetails;
import com.example.task_manager_backend.DTO.UpdatePassword;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Repository.UsersRepo;

import jakarta.persistence.EntityNotFoundException;
// import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addNewUser(User user) {
        // System.out.println(user.getPassword()+" This is password");
        User usr = userRepo.findByEmail(user.getEmail());
        if(usr != null){
            throw new IllegalStateException("User already present");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> usr = userRepo.findById(id);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not Found");
        }
        User users = usr.get();
        users.setProfilePic(user.getProfilePic());
        users.setUsername(user.getUsername());
        users.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(users);
    }

    public User getUser(Long id) {
        Optional<User> usr = userRepo.findById(id);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not Found");
        }
        return usr.get();
    }

    public User updateUserPassword(UpdatePassword password) {
        User usr = userRepo.findByEmail(password.getEmail());
        if(usr == null){
            throw new EntityNotFoundException("User not Found");
        }
    
        if(!password.getPassword().equals(password.getConfirmPassword())){
            throw new IllegalStateException("Password and Confirm Password Not match");
        }
        System.out.println("Password updated");
        usr.setPassword(passwordEncoder.encode(password.getPassword()));
        return userRepo.save(usr);
    }

    public User updateUserProfilePic(Long id, String profilePic) {
        Optional<User> usr = userRepo.findById(id);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not Found");
        }
        User user = usr.get();
        user.setProfilePic(profilePic);
        return userRepo.save(user);
    }

    public Page<User> getAllUsers(int page, int size) {
        PageRequest pages = PageRequest.of(page, size);
        return userRepo.findAll(pages);
    }

   
    public User validateUser(LoginDetails details) throws FailedLoginException{
        User usr = userRepo.findByEmail(details.getEmail());
        if(usr == null){
            throw new EntityNotFoundException("User not found");
        }
        else if(!passwordEncoder.matches(details.getPassword(), usr.getPassword())){
            throw new FailedLoginException("Invalid Password");
        }
        return usr;
    }

    public User verifyEmail(String email) {
        User usr = userRepo.findByEmail(email);
        if(usr == null){
            throw new EntityNotFoundException("User not found");
        }
        return usr;
    }
}
