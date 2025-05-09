package com.example.test_unit;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.test_unit.TodoItem;
import com.example.test_unit.TodoRepository;
import com.example.test_unit.TodoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TodoServiceInstrumentedTest {

    private TodoRepository repository;
    private TodoService todoService;

    @Before
    public void setUp() {
        repository = new TodoRepository();
        todoService = new TodoService(repository);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.test_unit", appContext.getPackageName());
    }

    @Test
    public void testAddAndCompleteTodo() {
        // Thêm một công việc mới
        String title = "Kiểm thử trên thiết bị thật";
        TodoItem todo = todoService.addTodo(title);

        // Kiểm tra công việc được thêm thành công
        assertNotNull(todo);
        assertEquals(title, todo.getTitle());
        assertFalse(todo.isCompleted());

        // Hoàn thành công việc
        TodoItem completed = todoService.completeTodo(todo.getId());

        // Kiểm tra công việc đã được đánh dấu hoàn thành
        assertTrue(completed.isCompleted());
    }
}