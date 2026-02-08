package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.bases.SubFooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new SubFooService());
    }

    @Test
    public void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }
}
