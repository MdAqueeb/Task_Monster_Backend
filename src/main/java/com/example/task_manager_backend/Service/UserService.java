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

import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Repository.UsersRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addNewUser(User user) {
        // System.out.println(user.getPassword()+" This is password");
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

    public User updateUserPassword(Long id, String password) {
        Optional<User> usr = userRepo.findById(id);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not Found");
        }
        User user = usr.get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
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
}
