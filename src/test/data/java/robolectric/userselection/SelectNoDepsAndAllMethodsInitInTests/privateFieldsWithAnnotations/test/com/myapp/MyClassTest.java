package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        ReflectionTestUtils.setField(myClassUnderTest, "fooService", null);
        ReflectionTestUtils.setField(myClassUnderTest, "barService", null);
        ReflectionTestUtils.setField(myClassUnderTest, "defaultBarId", "defaultBarId");
    }

    @Test
    public void testGetFooAndBar1() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}
