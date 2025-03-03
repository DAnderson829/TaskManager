package com.TaskManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired	
	private TaskRepository taskRepository;

	public Task createTask(String title, LocalDateTime completeBy, String description) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if (completeBy == null || completeBy.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Complete by date must be after creation date.");

		}
		Task task = new Task(title, completeBy, description);
		return taskRepository.save(task);
	}

	public boolean deleteTask(Long id) {
		if(taskRepository.findById(id).isPresent()) {
		taskRepository.deleteById(id);
		return true;
		}
		throw new IllegalArgumentException("Task does not exist.");
	}

	public Optional<Task> getTask(Long id) {
		return taskRepository.findById(id);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task updateTask(Task task, String title, LocalDateTime completeBy, String description, boolean isCompleted) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (completeBy == null || completeBy.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Complete by date must be after creation date.");
		}
		
		task.setTitle(title);
		task.setCompleteBy(completeBy);
		task.setDescription(description);
		task.setCompleted(isCompleted);
		return taskRepository.save(task);
	}

	public Task completeTask(Task task) {
		task.setCompleted(true);
		return taskRepository.save(task);
	}
	


}
