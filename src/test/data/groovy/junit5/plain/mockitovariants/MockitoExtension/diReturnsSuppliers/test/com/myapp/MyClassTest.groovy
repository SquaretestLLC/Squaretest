package com.myapp

import com.myapp.suppliers.Foo
import com.myapp.suppliers.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.util.function.*

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetFoosLazy() {
        // Setup
        // Configure FooService.getFoosLazy(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooSupplier = { foo } as Supplier
        when(mockFooService.getFoosLazy("fooId")).thenReturn(fooSupplier)

        // Run the test
        def result = myClassUnderTest.getFoosLazy("fooId")

        // Verify the results
    }

    @Test
    void testGetStringsLazy() {
        // Setup
        when(mockFooService.getStringsLazy("fooId")).thenReturn({ "value" } as Supplier)

        // Run the test
        def result = myClassUnderTest.getStringsLazy("fooId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetIntsLazy() {
        // Setup
        when(mockFooService.getIntsLazy("fooId")).thenReturn({ 0 } as IntSupplier)

        // Run the test
        def result = myClassUnderTest.getIntsLazy("fooId")

        // Verify the results
        assert 0 == result
    }

    @Test
    void testGetBoolsLazy() {
        // Setup
        when(mockFooService.getBoolsLazy("fooId")).thenReturn({ false } as BooleanSupplier)

        // Run the test
        def result = myClassUnderTest.getBoolsLazy("fooId")

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testGetDoublesLazy() {
        // Setup
        when(mockFooService.getDoublesLazy("fooId")).thenReturn({ 0.0d } as DoubleSupplier)

        // Run the test
        def result = myClassUnderTest.getDoublesLazy("fooId")

        // Verify the results
        assertEquals(0.0d, result, 0.0001d)
    }

    @Test
    void testGetLongsLazy() {
        // Setup
        when(mockFooService.getLongsLazy("fooId")).thenReturn({ 0L } as LongSupplier)

        // Run the test
        def result = myClassUnderTest.getLongsLazy("fooId")

        // Verify the results
        assert 0L == result
    }
}
