package com.myapp;

import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MyClassTest {

    @Test
    public void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    public void testGetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    public void testSetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.setValue("setValueParam"));
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
