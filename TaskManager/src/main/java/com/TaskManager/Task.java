package com.TaskManager;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskID;
	
	private String title;
	private boolean completed;
	public LocalDateTime creationDate;
	public LocalDateTime completeBy;
	
	public Task(String title, LocalDateTime completeBy){
		this.title = title;
		this.completed = false;
		this.creationDate = LocalDateTime.now();
		this.completeBy = completeBy;	
	}
	
	public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalDateTime completeBy) {
        this.completeBy = completeBy;
    }
}