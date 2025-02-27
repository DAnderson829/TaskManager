//package com.TaskManager;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class TaskTest {
//
//	@InjectMocks
//	private TaskService taskService;
//
//	private String title = "TestTitle";
//	private String description = "TestDescription";
//	private LocalDateTime completeBy = LocalDateTime.of(2025, 2, 24, 14, 30);
//	private String updatedTitle = "updated title";
//	private LocalDateTime updatedCompleteBy = LocalDateTime.of(2028, 2, 26, 14, 30);
//	private String updatedDescription = "updated description";
//	private LocalDateTime outdatedCompleteBy = LocalDateTime.of(2000, 2, 20, 8, 30);
//
//	@Test
//	public void createTaskTest() {
//		Task mockedTask1 = new Task(title, completeBy, description);
//		Task mockedTask2 = new Task(title, null, null);
//		mockedTask1.setTaskID(1L);
//		mockedTask2.setTaskID(2L);
//
//		when(taskService.createTask(title, completeBy, description)).thenReturn(mockedTask1);
//		when(taskService.createTask(title, null, null)).thenReturn(mockedTask2);
//
//		Task task1 = taskService.createTask(title, completeBy, description);
//		Task task2 = taskService.createTask(title, null, null);
//
//		assertNotNull(task1.getTaskID());
//		assertNotNull(task2.getTaskID());
//
//		assertEquals(title, task1.getTitle());
//		assertEquals(description, task1.getDescription());
//		assertEquals(completeBy, task1.getCompleteBy());
//
//		assertEquals(title, task2.getTitle());
//	}
//
//	@Test
//	public void testExceptions() {
//		Task mockTask = new Task(title, completeBy, description); 
//	    mockTask.setTaskID(1L);
//
//	    when(taskService.createTask(title, completeBy, description)).thenReturn(mockTask);
//	    
//	    //null title creation
//		when(taskService.createTask(eq(null), eq(completeBy), eq(description)))
//				.thenThrow(new IllegalArgumentException("Invalid title."));
//		//complete by before creation date
//		when(taskService.createTask(eq(title), eq(outdatedCompleteBy), eq(description)))
//				.thenThrow(new IllegalArgumentException("Complete by date must be after creation date."));
//		//updating to null title
//		when(taskService.updateTask(mockTask, eq(null), eq(completeBy), eq(description), eq(false)))
//				.thenThrow(new IllegalArgumentException("Invalid title."));
//		//updating to invalid complete by
//		when(taskService.updateTask(mockTask, eq(title), eq(outdatedCompleteBy), eq(description), eq(false)))
//				.thenThrow(new IllegalArgumentException("Complete by date must be after creation date."));
//
//		Exception invalidDateCreatingTask = assertThrows(IllegalArgumentException.class, () -> {
//			taskService.createTask(title, outdatedCompleteBy, description);
//		});
//		assertEquals("Complete by date must be after creation date.", invalidDateCreatingTask.getMessage());
//
//		Exception invalidDateUpdatingTask = assertThrows(IllegalArgumentException.class, () -> {
//			Task invalidCompleteBy = taskService.createTask(title, completeBy, description);
//			taskService.updateTask(invalidCompleteBy, title, outdatedCompleteBy, description, false);
//		});
//		assertEquals("Complete by date must be after creation date.", invalidDateUpdatingTask.getMessage());
//
//		Exception invalidTitleCreatingTask = assertThrows(IllegalArgumentException.class, () -> {
//			taskService.createTask(null, completeBy, description);
//		});
//		assertEquals("Invalid title.", invalidTitleCreatingTask.getMessage());
//
//		Exception invalidTitleUpdatingTask = assertThrows(IllegalArgumentException.class, () -> {
//			Task invalidCompleteBy = taskService.createTask(title, completeBy, description);
//			taskService.updateTask(invalidCompleteBy, null, completeBy, description, false);
//		});
//		assertEquals("Invalid title.", invalidTitleUpdatingTask.getMessage());
//	}
//
//	@Test
//	public void updateTaskTest() {
//		Task updateTask1 = new Task(title, completeBy, description);
//		Task updateTask2 = new Task(updatedTitle, updatedCompleteBy, updatedDescription);
//		updateTask1.setTaskID(1L);
//		updateTask2.setTaskID(2L);
//		updateTask2.setCompleted(true);
//
//		when(taskService.createTask(title, completeBy, description)).thenReturn(updateTask1);
//		when(taskService.updateTask(eq(updateTask1), eq(updatedTitle), eq(updatedCompleteBy), eq(updatedDescription),
//				eq(true))).thenReturn(updateTask2);
//
//		Task resultTask = taskService.updateTask(updateTask1, updatedTitle, updatedCompleteBy, updatedDescription,
//				true);
//
//		assertEquals(updatedTitle, resultTask.getTitle());
//		assertEquals(updatedCompleteBy, resultTask.getCompleteBy());
//		assertEquals(updatedDescription, resultTask.getDescription());
//		assertTrue(resultTask.isCompleted());
//	}
//
//	@Test
//	public void deleteTaskTest() {
//		Task task = taskService.createTask(title, completeBy, description);
//		task.setTaskID(1L);
//		Long taskId = task.getTaskID();
//
//		when(taskService.getTask(taskId)).thenReturn(Optional.of(task)).thenReturn(Optional.empty());
//
//		taskService.removeTask(taskId);
//
//		assertTrue(taskService.getTask(taskId).isEmpty());
//	}
//
//	@Test
//	public void getAllTasksTest() {
//		Task task = new Task(title, completeBy, description);
//		task.setTaskID(1L);
//
//		when(taskService.createTask(title, completeBy, description)).thenReturn(task);
//		when(taskService.getTask(task.getTaskID())).thenReturn(Optional.of(task));
//
//		assertTrue(taskService.getTask(task.getTaskID()).isPresent());
//	}
//
//}
