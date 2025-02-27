package com.TaskManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestParam String title,
                                           @RequestParam(required = false) LocalDateTime completeBy,
                                           @RequestParam(required = false) String description) {
        Task task = taskService.createTask(title, completeBy, description);
        return ResponseEntity.ok(task);
    }
	
	 @GetMapping("/{id}")
	    public ResponseEntity<Task> getTask(@PathVariable Long id) {
	        Optional<Task> task = taskService.getTask(id);
	        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	 
	 @GetMapping("/all")
	    public ResponseEntity<List<Task>> getAllTasks() {
	        List<Task> tasks = taskService.getAllTasks();
	        return ResponseEntity.ok(tasks);
	    }
	 
	 @PutMapping("/update/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable Long id,
	                                           @RequestParam String title,
	                                           @RequestParam(required = false) LocalDateTime completeBy,
	                                           @RequestParam(required = false) String description,
	                                           @RequestParam boolean isCompleted) {
	        Optional<Task> optionalTask = taskService.getTask(id);
	        if (optionalTask.isPresent()) {
	            Task updatedTask = taskService.updateTask(optionalTask.get(), title, completeBy, description, isCompleted);
	            return ResponseEntity.ok(updatedTask);
	        }
	        return ResponseEntity.notFound().build();
	    }
}
	