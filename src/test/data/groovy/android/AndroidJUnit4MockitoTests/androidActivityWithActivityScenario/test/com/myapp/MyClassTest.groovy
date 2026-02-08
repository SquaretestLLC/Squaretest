package com.myapp

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@LargeTest
class MyClassTest {

    @Test
    public void testLaunchActivity() {
        // Run the test
        try (ActivityScenario<MyClass> scenario = ActivityScenario.launch(MyClass.class)) {
            scenario.onActivity({ ActivityScenario.ActivityAction<MyClass> activity ->
                // Verify the results
            })
        }
    }
}
