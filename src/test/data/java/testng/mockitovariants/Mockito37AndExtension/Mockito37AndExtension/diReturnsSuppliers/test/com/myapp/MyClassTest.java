package com.myapp;

import com.myapp.suppliers.Foo;
import com.myapp.suppliers.FooService;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetFoosLazy() {
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
    public void testGetStringsLazy() {
        // Setup
        when(mockFooService.getStringsLazy("fooId")).thenReturn(() -> "value");

        // Run the test
        final String result = myClassUnderTest.getStringsLazy("fooId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetIntsLazy() {
        // Setup
        when(mockFooService.getIntsLazy("fooId")).thenReturn(() -> 0);

        // Run the test
        final int result = myClassUnderTest.getIntsLazy("fooId");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetBoolsLazy() {
        // Setup
        when(mockFooService.getBoolsLazy("fooId")).thenReturn(() -> false);

        // Run the test
        final boolean result = myClassUnderTest.getBoolsLazy("fooId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testGetDoublesLazy() {
        // Setup
        when(mockFooService.getDoublesLazy("fooId")).thenReturn(() -> 0.0);

        // Run the test
        final double result = myClassUnderTest.getDoublesLazy("fooId");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testGetLongsLazy() {
        // Setup
        when(mockFooService.getLongsLazy("fooId")).thenReturn(() -> 0L);

        // Run the test
        final long result = myClassUnderTest.getLongsLazy("fooId");

        // Verify the results
        assertEquals(0L, result);
    }
}
