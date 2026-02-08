package com.myapp;

import com.myapp.suppliers.Foo;
import com.myapp.suppliers.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
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
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetIntsLazy() {
        // Setup
        when(mockFooService.getIntsLazy("fooId")).thenReturn(() -> 0);

        // Run the test
        final int result = myClassUnderTest.getIntsLazy("fooId");

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testGetBoolsLazy() {
        // Setup
        when(mockFooService.getBoolsLazy("fooId")).thenReturn(() -> false);

        // Run the test
        final boolean result = myClassUnderTest.getBoolsLazy("fooId");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testGetDoublesLazy() {
        // Setup
        when(mockFooService.getDoublesLazy("fooId")).thenReturn(() -> 0.0);

        // Run the test
        final double result = myClassUnderTest.getDoublesLazy("fooId");

        // Verify the results
        assertThat(result).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testGetLongsLazy() {
        // Setup
        when(mockFooService.getLongsLazy("fooId")).thenReturn(() -> 0L);

        // Run the test
        final long result = myClassUnderTest.getLongsLazy("fooId");

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }
}
