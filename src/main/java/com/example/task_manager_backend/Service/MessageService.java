package com.example.task_manager_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.Messages;
import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Repository.MessageRepo;
import com.example.task_manager_backend.Repository.TaskRepo;
import com.example.task_manager_backend.Repository.UsersRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    public Messages addNewMessage(Long userid, Long taskid, Messages message) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found "+userid);
        }
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("Task not found "+taskid);
        }
        message.setUser(usr.get());
        message.setTask(tsk.get());
        return messageRepo.save(message);
    }

    @Transactional
    public Messages modifyMessage(Long userid, Long messageid, Messages task) {
        Optional<User> usr = userRepo.findById(userid);
        System.out.println("Yes modify message method inside");
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found "+userid);
        }
        Optional<Messages> msg = messageRepo.findById(messageid);
        if(!msg.isPresent()){
            throw new EntityNotFoundException("Message not found "+messageid);
        }
        User user = usr.get();
        Messages message = msg.get();
        if(user.getUserId().equals(message.getUser().getUserId())){
            System.out.println("message sets incomplete");
            message.setMessageDescription(task.getMessageDescription());
            System.out.println("message sets complete "+message.getMessageDescription());
            return messageRepo.save(message);
        }
        else{
            throw new IllegalStateException("You can't modify this message "+userid);
        }
    }

    public Messages removeMessage(Long userid, Long messageid) {
        Optional<User> usr = userRepo.findById(userid);
        if(!usr.isPresent()){
            throw new EntityNotFoundException("User not found "+userid);
        }
        Optional<Messages> msg = messageRepo.findById(messageid);
        if(!msg.isPresent()){
            throw new EntityNotFoundException("Message not found "+messageid);
        }
        User user = usr.get();
        Messages message = msg.get();
        if(user.getUserId().equals(message.getUser().getUserId())){
            messageRepo.deleteById(messageid);
            return message;
        }
        else{
            throw new IllegalStateException("You can't Delete this message "+userid);
        }
    }

    public List<Messages> getMessage(Long taskid) {
        Optional<Tasks> tsk = taskRepo.findById(taskid);
        if(!tsk.isPresent()){
            throw new EntityNotFoundException("task not found "+taskid);
        }
        List<Messages> msgs = messageRepo.findByTaskId(taskid);
        return msgs;
    }
}
