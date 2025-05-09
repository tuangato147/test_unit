package com.example.test_unit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Button;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoItem> {
    private final TodoService todoService;

    public TodoAdapter(Context context, List<TodoItem> todos, TodoService todoService) {
        super(context, 0, todos);
        this.todoService = todoService;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        CheckBox checkBoxCompleted = convertView.findViewById(R.id.checkBoxCompleted);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTitle.setText(todo.getTitle());
        checkBoxCompleted.setChecked(todo.isCompleted());

        // Xử lý sự kiện khi checkbox được click
        checkBoxCompleted.setOnClickListener(view -> {
            if (checkBoxCompleted.isChecked()) {
                todoService.completeTodo(todo.getId());
            }
            // Cập nhật danh sách
            notifyDataSetChanged();
        });

        // Xử lý sự kiện khi nút xóa được click
        buttonDelete.setOnClickListener(view -> {
            todoService.deleteTodo(todo.getId());
            // Cập nhật danh sách
            notifyDataSetChanged();
        });

        return convertView;
    }
}