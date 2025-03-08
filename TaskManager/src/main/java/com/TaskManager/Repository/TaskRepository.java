package com.TaskManager.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.TaskManager.Service.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByTitle(String title);

    List<Task> findByCompleted(boolean completed);

}
