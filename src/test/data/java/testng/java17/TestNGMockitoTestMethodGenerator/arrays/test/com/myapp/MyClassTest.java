package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.Socket;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() throws Exception {
        myClassUnderTest = new MyClass(new int[]{0}, new String[]{"stringArray"},
                new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    public void testCompareArray1() throws Exception {
        myClassUnderTest.compareArray(new String[]{"array"}, new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    public void testReturnTheArray1() {
        assertEquals(new String[]{"result"}, myClassUnderTest.returnTheArray());
    }

    @Test
    public void testReturnTheArrays1() {
        // Setup
        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays();

        // Verify the results
    }
}