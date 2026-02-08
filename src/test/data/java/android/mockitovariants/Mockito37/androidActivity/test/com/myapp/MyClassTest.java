package com.myapp;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyClassTest {

    @Rule
    public ActivityTestRule<MyClass> activityTestRule = new ActivityTestRule<>(MyClass.class, false, false);

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();

        // Run the test
        activityTestRule.launchActivity(intent);

        // Verify the results
    }
}
