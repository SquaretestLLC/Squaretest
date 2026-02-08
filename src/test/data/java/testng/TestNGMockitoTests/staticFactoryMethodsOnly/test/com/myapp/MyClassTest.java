package com.myapp;

import org.testng.annotations.Test;

import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class MyClassTest {

    @Test
    public void testOf() {
        // Run the test
        final MyClass<String, Throwable> result = MyClass.of("value");
        final Function<Throwable, ? extends RuntimeException> exceptionSupplier = val -> {
            return new RuntimeException("message");
        };
        assertEquals("result", result.onError(exceptionSupplier));
        assertEquals("result", result.get());
        assertEquals(new Exception("message"), result.getError());
        assertFalse(result.isError());
    }

    @Test
    public void testOfError() {
        // Run the test
        final MyClass<String, Throwable> result = MyClass.ofError(new Exception("message"));
        final Function<Throwable, ? extends RuntimeException> exceptionSupplier = val -> {
            return new RuntimeException("message");
        };
        assertEquals("result", result.onError(exceptionSupplier));
        assertEquals("result", result.get());
        assertEquals(new Exception("message"), result.getError());
        assertFalse(result.isError());
    }
}
