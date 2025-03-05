package com.TaskManager;

import java.time.LocalDateTime;

public class TaskRequest {
	private String title;
    private LocalDateTime completeBy;
    private String description;
    private boolean completed;
    
    public TaskRequest() {}

    public TaskRequest(String title, LocalDateTime completeBy, String description, boolean completed) {
        this.title = title;
        this.completeBy = completeBy;
        this.description = description;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalDateTime completeBy) {
        this.completeBy = completeBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
