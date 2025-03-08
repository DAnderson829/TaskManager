package com.TaskManager.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.TaskManager.Repository.TaskRepository;
import com.TaskManager.Service.Task;
import com.TaskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<String> getTasks() {
        if(taskService.getAllTasks().isEmpty()){
            return ResponseEntity.ok("No tasks");
        }
        List<Task> tasks = taskService.getAllTasks();
        String response = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestParam String title,  @RequestParam String description) {
        Task task = taskService.createTask(title, description);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> task = taskService.getTask(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delete/allTasks")
    public void deleteAllTasks(){
        taskService.deleteAllTasks();
    }

    @GetMapping("/init")
    public void initTasks(){
        taskService.init();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestParam String title,  @RequestParam String description, @RequestParam boolean isCompleted  ) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            Task updatedTask = taskService.updateTask(id, title, description, isCompleted);
            return ResponseEntity.ok(updatedTask.toString());
        }
        return ResponseEntity.notFound().build();
    }


}