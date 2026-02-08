package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection() {
        final MyClass myClassUnderTest = MyClass.getInstance();
        assertNull(myClassUnderTest.createNewConnection());
    }
}
