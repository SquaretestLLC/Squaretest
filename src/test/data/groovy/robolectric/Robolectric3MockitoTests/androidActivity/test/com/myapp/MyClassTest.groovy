package com.myapp

import android.content.Intent
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testLaunchActivity() {
        // Setup
        def intent = new Intent()
        def activityController = Robolectric.buildActivity(MyClass.class, intent)

        // Run the test
        def myClassUnderTest = activityController.setup().get()

        // Verify the results
    }

    @Test
    void testCreateLaunchIntent() {
        // Setup
        // Run the test
        def result = MyClass.createLaunchIntent(RuntimeEnvironment.application, "name")

        // Verify the results
    }
}
