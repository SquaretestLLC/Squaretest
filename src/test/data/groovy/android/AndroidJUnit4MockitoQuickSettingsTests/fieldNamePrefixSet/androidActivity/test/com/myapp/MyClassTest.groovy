package com.myapp

import android.content.Intent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@LargeTest
class MyClassTest {

    @Rule
    public ActivityTestRule<MyClass> activityTestRule = new ActivityTestRule<>(MyClass.class, false, false)

    @Test
    void testLaunchActivity() {
        // Setup
        def intent = new Intent()

        // Run the test
        activityTestRule.launchActivity(intent)

        // Verify the results
    }
}
