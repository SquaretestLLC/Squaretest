package com.myapp;

import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
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
