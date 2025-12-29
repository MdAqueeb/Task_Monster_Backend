package com.example.task_manager_backend.Repository;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task_manager_backend.Entities.Tasks;
import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Entities.Tasks.TaskStatus;
// import com.example.task_manager_backend.Entities.User;
import com.example.task_manager_backend.Entities.Tasks.Trash;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Long>{
    
    @Query(
    value = "SELECT * FROM task WHERE userid = :userid AND task_trash = :nottrash",
    countQuery = "SELECT COUNT(*) FROM task WHERE userid = :userid AND task_trash = :nottrash",
    nativeQuery = true
    )
    Page<Tasks> findTasksByUser(
        @Param("userid") Long userid, @Param("nottrash") Trash nottrash,
        Pageable pageable
    );

    @Query(value = "SELECT * FROM task WHERE task_id = :taskid AND task_trash = :trash", nativeQuery = true)
    Optional<Tasks> findBytaskId(@Param("taskid") Long taskid, @Param("trash") Trash nottrash);

    @Query(value = "SELECT COUNT(*) FROM task WHERE userid = :userid", nativeQuery = true)
    Long countOfTask(@Param("userid") Long userid);

    @Query(value = "SELECT COUNT(*) FROM task WHERE userid = :user AND task_status = :taskStatus", nativeQuery = true)
    Integer countOfComplition(@Param("user") Long user, @Param("taskStatus") String taskStatus);

    @Query(value = "SELECT COUNT(*) FROM task WHERE userid = :user AND task_status = :taskStatus", nativeQuery = true)
    Integer countOfInProgress(@Param("user") Long user, @Param("taskStatus") String taskStatus);
    
    @Query(value = "SELECT COUNT(*) FROM task WHERE userid = :user AND task_status = :taskStatus", nativeQuery = true)
    Integer countOfOverDue(@Param("user") Long user, @Param("taskStatus") String taskStatus);
    @Query(value = "SELECT COUNT(*) FROM task WHERE userid = :user AND task_status = :taskStatus", nativeQuery = true)
    Integer countOfTODO(@Param("user") Long user, @Param("taskStatus") String taskStatus);

}
