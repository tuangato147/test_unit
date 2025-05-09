package com.example.test_unit;

import com.example.test_unit.TodoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoRepository {
    private final Map<Long, TodoItem> todos = new HashMap<>();
    private Long nextId = 1L;

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setId(nextId++);
        }
        todos.put(todoItem.getId(), todoItem);
        return todoItem;
    }

    public TodoItem findById(Long id) {
        return todos.get(id);
    }

    public List<TodoItem> findAll() {
        return new ArrayList<>(todos.values());
    }

    public void delete(Long id) {
        todos.remove(id);
    }

    public void clear() {
        todos.clear();
        nextId = 1L;
    }
}