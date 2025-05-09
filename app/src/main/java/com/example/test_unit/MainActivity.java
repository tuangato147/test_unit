package com.example.test_unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TodoService todoService;
    private TodoAdapter adapter;
    private EditText editTextTodo;
    private ListView listViewTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo repository và service
        TodoRepository repository = new TodoRepository();
        todoService = new TodoService(repository);

        // Liên kết UI
        editTextTodo = findViewById(R.id.editTextTodo);
        Button buttonAddTodo = findViewById(R.id.buttonAddTodo);
        listViewTodos = findViewById(R.id.listViewTodos);

        // Thiết lập adapter
        adapter = new TodoAdapter(this, todoService.getAllTodos(), todoService);
        listViewTodos.setAdapter(adapter);

        // Thiết lập sự kiện click
        buttonAddTodo.setOnClickListener(v -> {
            String title = editTextTodo.getText().toString().trim();
            try {
                todoService.addTodo(title);
                // Cập nhật adapter sau khi thêm
                adapter.clear();
                adapter.addAll(todoService.getAllTodos());
                adapter.notifyDataSetChanged();

                Toast.makeText(this, "Đã thêm công việc: " + title, Toast.LENGTH_SHORT).show();
                editTextTodo.setText("");
            } catch (IllegalArgumentException e) {
                Toast.makeText(this, "Tiêu đề không được để trống", Toast.LENGTH_SHORT).show();
            }
        });
    }
}