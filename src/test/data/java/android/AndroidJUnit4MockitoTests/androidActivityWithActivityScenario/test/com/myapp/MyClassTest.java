package com.myapp;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyClassTest {

    @Test
    public void testLaunchActivity() {
        // Run the test
        try (final ActivityScenario<MyClass> scenario = ActivityScenario.launch(MyClass.class)) {
            scenario.onActivity(activity -> {
                // Verify the results
            });
        }
    }
}
