package com.example.test_unit;

import com.example.test_unit.TodoItem;
import com.example.test_unit.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodo(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be empty");
        }
        return repository.save(new TodoItem(title));
    }

    public TodoItem getTodo(Long id) {
        TodoItem todo = repository.findById(id);
        if (todo == null) {
            throw new IllegalArgumentException("Todo not found with id: " + id);
        }
        return todo;
    }

    public List<TodoItem> getAllTodos() {
        return repository.findAll();
    }

    public TodoItem completeTodo(Long id) {
        TodoItem todo = getTodo(id);
        todo.setCompleted(true);
        return repository.save(todo);
    }

    public void deleteTodo(Long id) {
        getTodo(id); // Kiểm tra xem todo có tồn tại không
        repository.delete(id);
    }

    public List<TodoItem> getTodosByStatus(boolean completed) {
        return repository.findAll().stream()
                .filter(todo -> todo.isCompleted() == completed)
                .collect(Collectors.toList());
    }
}