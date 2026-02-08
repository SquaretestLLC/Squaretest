package com.myapp;

import com.myapp.suppliers.Foo;
import com.myapp.suppliers.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFoosLazy() {
        // Setup
        // Configure FooService.getFoosLazy(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Supplier<Foo> fooSupplier = () -> foo;
        when(mockFooService.getFoosLazy("fooId")).thenReturn(fooSupplier);

        // Run the test
        final Foo result = myClassUnderTest.getFoosLazy("fooId");

        // Verify the results
    }

    @Test
    void testGetStringsLazy() {
        // Setup
        when(mockFooService.getStringsLazy("fooId")).thenReturn(() -> "value");

        // Run the test
        final String result = myClassUnderTest.getStringsLazy("fooId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetIntsLazy() {
        // Setup
        when(mockFooService.getIntsLazy("fooId")).thenReturn(() -> 0);

        // Run the test
        final int result = myClassUnderTest.getIntsLazy("fooId");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testGetBoolsLazy() {
        // Setup
        when(mockFooService.getBoolsLazy("fooId")).thenReturn(() -> false);

        // Run the test
        final boolean result = myClassUnderTest.getBoolsLazy("fooId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetDoublesLazy() {
        // Setup
        when(mockFooService.getDoublesLazy("fooId")).thenReturn(() -> 0.0);

        // Run the test
        final double result = myClassUnderTest.getDoublesLazy("fooId");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testGetLongsLazy() {
        // Setup
        when(mockFooService.getLongsLazy("fooId")).thenReturn(() -> 0L);

        // Run the test
        final long result = myClassUnderTest.getLongsLazy("fooId");

        // Verify the results
        assertEquals(0L, result);
    }
}
