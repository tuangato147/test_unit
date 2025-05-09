package com.example.test_unit;

import com.example.test_unit.TodoItem;
import com.example.test_unit.TodoRepository;
import com.example.test_unit.TodoService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

public class TodoServiceTest {

    private TodoRepository repository;
    private TodoService todoService;

    @Before
    public void setUp() {
        repository = new TodoRepository();
        todoService = new TodoService(repository);
    }

    @Test
    public void addTodo_shouldCreateNewTodoWithCorrectTitle() {
        // Chuẩn bị
        String todoTitle = "Hoàn thành bài kiểm thử";

        // Thực hiện
        TodoItem result = todoService.addTodo(todoTitle);

        // Kiểm tra
        assertEquals(todoTitle, result.getTitle());
        assertFalse(result.isCompleted());
        assertNotNull(result.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTodo_shouldThrowExceptionForEmptyTitle() {
        todoService.addTodo("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addTodo_shouldThrowExceptionForNullTitle() {
        todoService.addTodo(null);
    }

    @Test
    public void completeTodo_shouldMarkTodoAsCompleted() {
        // Chuẩn bị
        TodoItem todo = todoService.addTodo("Công việc cần hoàn thành");
        assertFalse(todo.isCompleted());

        // Thực hiện
        TodoItem completedTodo = todoService.completeTodo(todo.getId());

        // Kiểm tra
        assertTrue(completedTodo.isCompleted());
        assertTrue(todoService.getTodo(todo.getId()).isCompleted());
    }

    @Test
    public void getTodosByStatus_shouldReturnOnlyCompletedTodos() {
        // Chuẩn bị
        todoService.addTodo("Nhiệm vụ 1");
        TodoItem todo2 = todoService.addTodo("Nhiệm vụ 2");
        todoService.addTodo("Nhiệm vụ 3");
        
        // Hoàn thành nhiệm vụ 2
        todoService.completeTodo(todo2.getId());

        // Thực hiện
        List<TodoItem> completedTodos = todoService.getTodosByStatus(true);
        List<TodoItem> uncompletedTodos = todoService.getTodosByStatus(false);

        // Kiểm tra
        assertEquals(1, completedTodos.size());
        assertEquals(2, uncompletedTodos.size());
        assertTrue(completedTodos.get(0).isCompleted());
        assertFalse(uncompletedTodos.get(0).isCompleted());
        assertFalse(uncompletedTodos.get(1).isCompleted());
    }

    @Test
    public void deleteTodo_shouldRemoveTodoFromRepository() {
        // Chuẩn bị
        TodoItem todo = todoService.addTodo("Nhiệm vụ cần xóa");
        assertEquals(1, todoService.getAllTodos().size());

        // Thực hiện
        todoService.deleteTodo(todo.getId());

        // Kiểm tra
        assertEquals(0, todoService.getAllTodos().size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getTodo_shouldThrowExceptionForNonExistentId() {
        todoService.getTodo(999L);
    }
}