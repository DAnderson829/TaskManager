package com.TaskManager;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByTitle(String title);

    List<Task> findByCompleted(boolean completed);

    List<Task> findByCompleteByBefore(LocalDateTime date);

}
