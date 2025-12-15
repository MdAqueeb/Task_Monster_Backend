package com.example.task_manager_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.task_manager_backend.Controller.List;
import java.util.List;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Repository.UsersRepo;

@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;

    public User addNewUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewUser'");
    }

    public User updateUser(Long id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    public User getUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    public User updateUserPassword(Long id, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserPassword'");
    }

    public User updateUserProfilePic(Long id, String profilePic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserProfilePic'");
    }

    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }
}
