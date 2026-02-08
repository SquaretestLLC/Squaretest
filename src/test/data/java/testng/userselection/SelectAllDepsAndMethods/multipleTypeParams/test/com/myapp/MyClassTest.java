package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testLeftGetterAndSetter() {
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    public void testGetValue() {
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    public void testSetValue() {
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testSetValue_ThrowsUnsupportedOperationException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testSetValue_ThrowsClassCastException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testSetValue_ThrowsNullPointerException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSetValue_ThrowsIllegalArgumentException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testSetValue_ThrowsIllegalStateException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test
    public void testComparingByKey1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testComparingByValue1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testComparingByKey2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey(cmp);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testComparingByValue2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue(cmp);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
