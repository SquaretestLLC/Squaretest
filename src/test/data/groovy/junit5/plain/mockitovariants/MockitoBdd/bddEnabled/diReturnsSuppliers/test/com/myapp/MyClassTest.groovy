package com.myapp

import com.myapp.suppliers.Foo
import com.myapp.suppliers.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.function.*

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
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
        given(mockFooService.getFoosLazy("fooId")).willReturn(fooSupplier)

        // Run the test
        def result = myClassUnderTest.getFoosLazy("fooId")

        // Verify the results
    }

    @Test
    void testGetStringsLazy() {
        // Setup
        given(mockFooService.getStringsLazy("fooId")).willReturn({ "value" } as Supplier)

        // Run the test
        def result = myClassUnderTest.getStringsLazy("fooId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetIntsLazy() {
        // Setup
        given(mockFooService.getIntsLazy("fooId")).willReturn({ 0 } as IntSupplier)

        // Run the test
        def result = myClassUnderTest.getIntsLazy("fooId")

        // Verify the results
        assert 0 == result
    }

    @Test
    void testGetBoolsLazy() {
        // Setup
        given(mockFooService.getBoolsLazy("fooId")).willReturn({ false } as BooleanSupplier)

        // Run the test
        def result = myClassUnderTest.getBoolsLazy("fooId")

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testGetDoublesLazy() {
        // Setup
        given(mockFooService.getDoublesLazy("fooId")).willReturn({ 0.0d } as DoubleSupplier)

        // Run the test
        def result = myClassUnderTest.getDoublesLazy("fooId")

        // Verify the results
        assertEquals(0.0d, result, 0.0001d)
    }

    @Test
    void testGetLongsLazy() {
        // Setup
        given(mockFooService.getLongsLazy("fooId")).willReturn({ 0L } as LongSupplier)

        // Run the test
        def result = myClassUnderTest.getLongsLazy("fooId")

        // Verify the results
        assert 0L == result
    }
}
