package com.example.task_manager_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Notifications;
import com.example.task_manager_backend.Repository.NotificationRepo;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    public List<Notifications> getAllNotifications() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotifications'");
    }

    public List<Notifications> getAllNotifications(Long userid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotifications'");
    }

    public Notifications getAllNotifications(Long userid, Long notificationid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotifications'");
    }

    public Notifications addNotification(Long userid, Long anotherid, Notifications notification) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNotification'");
    }
}
