package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyClassTest {

    @Test
    void testOf() {
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
    void testOfError() {
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
