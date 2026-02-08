package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyClassTest {

    @Test
    void testGetLowercaseColor() {
        // Run the test
        final String redResult = MyClass.RED.getLowercaseColor();
        final String blueResult = MyClass.BLUE.getLowercaseColor();
        final String greenResult = MyClass.GREEN.getLowercaseColor();
        final String purpleResult = MyClass.PURPLE.getLowercaseColor();

        // Verify the results
        assertEquals("result", redResult);
        assertEquals("result", blueResult);
        assertEquals("result", greenResult);
        assertEquals("result", purpleResult);
    }

    @Test
    void testGetUppercaseColor() {
        // Run the test
        final String redResult = MyClass.RED.getUppercaseColor();
        final String blueResult = MyClass.BLUE.getUppercaseColor();
        final String greenResult = MyClass.GREEN.getUppercaseColor();
        final String purpleResult = MyClass.PURPLE.getUppercaseColor();

        // Verify the results
        assertEquals("result", redResult);
        assertEquals("result", blueResult);
        assertEquals("result", greenResult);
        assertEquals("result", purpleResult);
    }

    @Test
    void testIsGreen() {
        // Run the test
        final boolean redResult = MyClass.RED.isGreen();
        final boolean blueResult = MyClass.BLUE.isGreen();
        final boolean greenResult = MyClass.GREEN.isGreen();
        final boolean purpleResult = MyClass.PURPLE.isGreen();

        // Verify the results
        assertTrue(redResult);
        assertTrue(blueResult);
        assertTrue(greenResult);
        assertTrue(purpleResult);
    }

    @Test
    void testToString() {
        // Run the test
        final String redResult = MyClass.RED.toString();
        final String blueResult = MyClass.BLUE.toString();
        final String greenResult = MyClass.GREEN.toString();
        final String purpleResult = MyClass.PURPLE.toString();

        // Verify the results
        assertEquals("result", redResult);
        assertEquals("result", blueResult);
        assertEquals("result", greenResult);
        assertEquals("result", purpleResult);
    }

    @Test
    void testIsSupported() {
        assertTrue(MyClass.isSupported("colorName"));
    }
}
