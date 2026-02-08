package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
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
