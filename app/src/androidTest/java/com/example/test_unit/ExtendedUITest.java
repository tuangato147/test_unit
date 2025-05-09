package com.example.test_unit;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExtendedUITest {

    @Before
    public void setup() {
        ActivityScenario.launch(MainActivity.class);
    }
    
    private void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCompleteWorkflow() {
        // Thêm 3 công việc
        addTodo("Mua sắm thực phẩm");
        waitFor(1500);
        
        addTodo("Hoàn thành báo cáo");
        waitFor(1500);
        
        addTodo("Học Unit Testing");
        waitFor(1500);
        
        // Kiểm tra danh sách
        waitFor(1000);
        
        // Đánh dấu công việc thứ 2 là hoàn thành
        onData(anything())
                .inAdapterView(withId(R.id.listViewTodos))
                .atPosition(1)
                .onChildView(withId(R.id.checkBoxCompleted))
                .perform(click());
        waitFor(2000);
        
        // Xóa công việc thứ 3
        onData(anything())
                .inAdapterView(withId(R.id.listViewTodos))
                .atPosition(2)
                .onChildView(withId(R.id.buttonDelete))
                .perform(click());
        waitFor(2000);
        
        // Thêm một công việc mới
        addTodo("Chạy kiểm thử UI thành công");
        waitFor(3000); // Đợi lâu hơn để xem kết quả cuối cùng
    }
    
    private void addTodo(String title) {
        // Nhập công việc
        onView(withId(R.id.editTextTodo))
                .perform(replaceText(title));
        waitFor(1000);
        
        // Đóng bàn phím
        onView(withId(R.id.editTextTodo))
                .perform(closeSoftKeyboard());
        waitFor(500);
        
        // Click nút thêm
        onView(withId(R.id.buttonAddTodo))
                .perform(click());
        waitFor(1000);
    }
}