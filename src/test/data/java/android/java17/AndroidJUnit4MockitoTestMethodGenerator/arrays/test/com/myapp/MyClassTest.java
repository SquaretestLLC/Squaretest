package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.Socket;

import static org.junit.Assert.assertArrayEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.returnTheArray());
    }

    @Test
    public void testReturnTheArrays1() {
        // Setup
        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays();

        // Verify the results
    }
}