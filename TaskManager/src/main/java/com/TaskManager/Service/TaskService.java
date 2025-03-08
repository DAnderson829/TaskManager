package com.TaskManager.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.TaskManager.Repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired	
	private TaskRepository taskRepository;

	public Task createTask(String title, LocalDateTime completeBy, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if (completeBy != null && completeBy.isBefore(LocalDateTime.now())) {
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

	public void deleteAllTasks(){taskRepository.deleteAll(); }

	public Task updateTask(Long id, String title, LocalDateTime completeBy, String description, boolean isCompleted) {
		if(taskRepository.findById(id).isEmpty()) {
			throw new IllegalArgumentException("Task not found");
		}


		Optional<Task> optionalTask = taskRepository.findById(id);
		Task task = optionalTask.get();

		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (completeBy.isBefore(LocalDateTime.now())) {
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
	

	@PostConstruct
	public void init(){
		if (taskRepository.findAll().isEmpty()) {
			createTask("Pick up from school", null, null);
			createTask("Go for a run", LocalDateTime.of(2027, 12, 12, 12, 12), "10 miles");
			createTask("Meeting", null, "Prepare");
			createTask("Read", LocalDateTime.now().plusDays(5), "50 pages");
			createTask("Go to gym", null, "Leg day");
			createTask("Dinner", LocalDateTime.now().plusHours(5), null);
		}
	}

}
