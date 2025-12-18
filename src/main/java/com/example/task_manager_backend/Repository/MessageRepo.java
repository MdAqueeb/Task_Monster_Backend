package com.example.task_manager_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task_manager_backend.Entities.Messages;

@Repository
public interface MessageRepo extends JpaRepository<Messages, Long> {

    @Query(value = "SELECT * FROM messages WHERE task_id = :taskid", nativeQuery = true)
    List<Messages> findByTaskId(@Param("taskid") Long taskid);
    
}
