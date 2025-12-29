package com.example.task_manager_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task_manager_backend.Entities.Sub_Task;

@Repository
public interface SubTaskRepo extends JpaRepository<Sub_Task, Long> {

    @Query(value = "SELECT * FROM sub_task WHERE task_id = :taskid", nativeQuery = true)
    List<Sub_Task> findByTaskId(@Param("taskid") Long taskid);
    
}
