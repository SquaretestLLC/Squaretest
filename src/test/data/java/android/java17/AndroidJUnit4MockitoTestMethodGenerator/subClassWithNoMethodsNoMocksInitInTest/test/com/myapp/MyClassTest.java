package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testGetItems11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    public void testGetItems21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(List.of("value"), result);
    }
}