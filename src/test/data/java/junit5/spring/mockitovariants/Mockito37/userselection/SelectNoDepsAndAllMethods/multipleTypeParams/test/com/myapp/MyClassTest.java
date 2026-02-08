package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testLeftGetterAndSetter() {
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    void testGetValue() {
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    void testSetValue() {
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.setValue("setValueParam"));
    }

    @Test
    void testComparingByKey1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testComparingByValue1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testComparingByKey2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey(cmp);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testComparingByValue2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue(cmp);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
