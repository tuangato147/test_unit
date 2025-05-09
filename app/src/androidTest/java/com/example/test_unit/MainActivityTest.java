package com.example.test_unit;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

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
    public void addMultipleTodosWithDelay() {
        // Kiểm tra giao diện hiển thị đúng
        onView(withId(R.id.editTextTodo)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddTodo)).check(matches(isDisplayed()));
        waitFor(1000); // Đợi 1 giây

        // Thêm công việc #1
        onView(withId(R.id.editTextTodo))
                .perform(replaceText("Công việc số 1"));
        waitFor(1000); // Đợi 1 giây

        onView(withId(R.id.editTextTodo))
                .perform(closeSoftKeyboard());
        waitFor(500); // Đợi 0.5 giây

        onView(withId(R.id.buttonAddTodo))
                .perform(click());
        waitFor(1500); // Đợi 1.5 giây

        // Kiểm tra EditText đã được xóa
        onView(withId(R.id.editTextTodo))
                .check(matches(withText("")));
        waitFor(1000); // Đợi 1 giây

        // Thêm công việc #2
        onView(withId(R.id.editTextTodo))
                .perform(replaceText("Công việc số 2 cần hoàn thành"));
        waitFor(1000); // Đợi 1 giây

        onView(withId(R.id.editTextTodo))
                .perform(closeSoftKeyboard());
        waitFor(500); // Đợi 0.5 giây

        onView(withId(R.id.buttonAddTodo))
                .perform(click());
        waitFor(1500); // Đợi 1.5 giây

        // Thêm công việc #3 - Sử dụng nhập từng ký tự để thấy rõ hơn
        onView(withId(R.id.editTextTodo))
                .perform(click(), clearText());
        waitFor(500);

        // Nhập từng chữ một để thấy rõ hơn
        onView(withId(R.id.editTextTodo)).perform(typeText("K"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("i"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("e"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("m"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText(" "));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("t"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("h"));
        waitFor(200);
        onView(withId(R.id.editTextTodo)).perform(typeText("u"));
        waitFor(500);

        onView(withId(R.id.editTextTodo))
                .perform(closeSoftKeyboard());
        waitFor(1000); // Đợi 1 giây

        onView(withId(R.id.buttonAddTodo))
                .perform(click());
        waitFor(2000); // Đợi 2 giây để kết thúc kiểm thử
    }
}