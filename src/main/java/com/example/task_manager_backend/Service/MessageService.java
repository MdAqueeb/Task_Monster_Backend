package com.example.task_manager_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Messages;
import com.example.task_manager_backend.Repository.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Messages addNewMessage(Messages message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewMessage'");
    }

    public Messages modifyMessage(Long id, Messages task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyMessage'");
    }

    public Messages removeMessage(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMessage'");
    }

    public Messages getMessage(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }
}
