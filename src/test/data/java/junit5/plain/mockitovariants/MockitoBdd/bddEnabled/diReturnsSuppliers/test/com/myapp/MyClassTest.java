package com.myapp;

import com.myapp.suppliers.Foo;
import com.myapp.suppliers.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
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
        given(mockFooService.getFoosLazy("fooId")).willReturn(fooSupplier);

        // Run the test
        final Foo result = myClassUnderTest.getFoosLazy("fooId");

        // Verify the results
    }

    @Test
    void testGetStringsLazy() {
        // Setup
        given(mockFooService.getStringsLazy("fooId")).willReturn(() -> "value");

        // Run the test
        final String result = myClassUnderTest.getStringsLazy("fooId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetIntsLazy() {
        // Setup
        given(mockFooService.getIntsLazy("fooId")).willReturn(() -> 0);

        // Run the test
        final int result = myClassUnderTest.getIntsLazy("fooId");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testGetBoolsLazy() {
        // Setup
        given(mockFooService.getBoolsLazy("fooId")).willReturn(() -> false);

        // Run the test
        final boolean result = myClassUnderTest.getBoolsLazy("fooId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testGetDoublesLazy() {
        // Setup
        given(mockFooService.getDoublesLazy("fooId")).willReturn(() -> 0.0);

        // Run the test
        final double result = myClassUnderTest.getDoublesLazy("fooId");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testGetLongsLazy() {
        // Setup
        given(mockFooService.getLongsLazy("fooId")).willReturn(() -> 0L);

        // Run the test
        final long result = myClassUnderTest.getLongsLazy("fooId");

        // Verify the results
        assertEquals(0L, result);
    }
}
