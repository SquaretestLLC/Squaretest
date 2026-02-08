package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingWithList1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(List.of("value"));

        // Verify the results
    }

    @Test
    public void testDoSomethingWithMultimap1() {
        // Setup
        final Map<String, List<String>> theMap = Map.ofEntries(Map.entry("value", List.of("value")));

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap);

        // Verify the results
    }

    @Test
    public void testStaticDoSomethingWithList1() {
        assertEquals("result", MyClass.staticDoSomethingWithList(List.of("value")));
    }
}