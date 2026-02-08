package com.myapp;

import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();
        final ActivityController<MyClass> activityController = Robolectric.buildActivity(MyClass.class, intent);

        // Run the test
        final MyClass myClassUnderTest = activityController.setup().get();

        // Verify the results
    }

    @Test
    public void testCreateLaunchIntent() {
        // Setup
        // Run the test
        final Intent result = MyClass.createLaunchIntent(RuntimeEnvironment.application, "name");

        // Verify the results
    }
}
