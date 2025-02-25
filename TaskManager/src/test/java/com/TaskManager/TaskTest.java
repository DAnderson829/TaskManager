package com.TaskManager;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
	
	@Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;
    
    private String title = "TestTitle";
    private String description = "TestDescription";
    private LocalDateTime completeBy = LocalDateTime.of(2025,2,24,14,30);
    private LocalDateTime creationDate = LocalDateTime.of(2025,2,24,15,0);	
    private String updatedTitle = "updated title";
	private LocalDateTime updatedCompleteBy = LocalDateTime.of(2028,2,26,14,30);
	private String updatedDescription = "updated description";
    
    @Test
	public void createTaskTest() {
    	//Tests for null description and completeBy date which should pass
        Task task1 = taskService.createTask(title, null, null);
    	//Tests for nullcompleteBy date which should pass
        Task task2 = taskService.createTask(title, null, description);
    	//Tests for null description which should pass
        Task task3 = taskService.createTask(title, completeBy, null);
    	//Normal task
        Task task4 = taskService.createTask(title, completeBy, description);
        
        //Swaps creationDate and CompleteBy which should pass error because completeBy cant be before creationDate
        Task task5 = taskService.createTask(title, creationDate, description);
        task5.setCreationDate(completeBy);

        assertNotNull(task1.getTaskID());
        assertNotNull(task2.getTaskID());
        assertNotNull(task3.getTaskID());
        assertNotNull(task4.getTaskID());
        assertNotNull(task5.getTaskID());
        
        assertEquals(title, task1.getTitle());
        assertEquals(title, task2.getTitle());
        assertEquals(title, task3.getTitle());
        assertEquals(title, task4.getTitle());
        assertEquals(title, task5.getTitle());
        
        assertEquals(description, task1.getDescription());
        assertEquals(description, task2.getDescription());
        assertEquals(description, task3.getDescription());
        assertEquals(description, task4.getDescription());
        assertEquals(description, task5.getDescription());
        
        assertEquals(completeBy, task1.getCompleteBy());
        assertEquals(completeBy, task2.getCompleteBy());
        assertEquals(completeBy, task3.getCompleteBy());
        assertEquals(completeBy, task4.getCompleteBy());
        
        assertTrue(task1.getCompleteBy().isAfter(LocalDateTime.now()));
        assertTrue(task2.getCompleteBy().isAfter(LocalDateTime.now()));
        assertTrue(task3.getCompleteBy().isAfter(LocalDateTime.now()));
        assertTrue(task4.getCompleteBy().isAfter(LocalDateTime.now()));
        //should be false because complete by data is before creation date
        assertFalse(task5.getCompleteBy().isAfter(LocalDateTime.now()));
        
	}
    
    @Test
    public void testExceptions() {
    	   
    }
    
    @Test
	public void updateTaskTest() {
    	Task updateTask = taskService.createTask(title, completeBy, description);
    
    	
    	updateTask.setTitle(updatedTitle);
    	updateTask.setCompleteBy(updatedCompleteBy);
    	updateTask.setDescription(updatedDescription);
    	updateTask.setCompleted(true);
    	
    	assertEquals(updatedTitle, updateTask.getTitle());
    	assertEquals(updatedCompleteBy, updateTask.getCompleteBy());
    	assertEquals(updatedDescription, updateTask.getDescription());
    	assertTrue(updateTask.isCompleted());

    	
    	
	}
    @Test
	public void deleteTaskTest() {
		
	}
    @Test
	public void getAllTasksTest() {
		
	}
	
	
}
