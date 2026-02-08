package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() throws Exception {
        myClassUnderTest = new MyClass(new int[]{0}, new String[]{"stringArray"},
                new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    public void testCompareArray() throws Exception {
        myClassUnderTest.compareArray(new String[]{"array"}, new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    public void testReturnTheArray() {
        assertThat(myClassUnderTest.returnTheArray()).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testReturnTheArrays() {
        // Setup
        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays();

        // Verify the results
    }
}
