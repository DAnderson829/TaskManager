package com.TaskManager;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskID;
	
	@Column(name = "Title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "Completed", nullable = false)
	private boolean completed;
	
	@Column(name = "CreationDate", nullable = false)
	private LocalDateTime creationDate;
	
	@Column(name = "CompleteBy", nullable = true)
	private LocalDateTime completeBy;
	
	@Column(name = "Description", nullable = true)
	private String description;
	
	public Task(String title, LocalDateTime completeBy, String description){
		this.title = title;
		this.completed = false;
		this.creationDate = LocalDateTime.now();
		this.completeBy = completeBy;
		this.description = description;
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
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getDescription() {
    	return description;
    }
}