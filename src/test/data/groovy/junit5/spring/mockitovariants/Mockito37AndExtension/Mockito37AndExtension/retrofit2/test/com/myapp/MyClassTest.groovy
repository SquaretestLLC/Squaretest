package com.myapp

import com.myapp.retro.Foo
import com.myapp.retro.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.mock.Calls

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testListFoos() {
        // Setup
        // Configure FooService.listFoos(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def listCall = Calls.response([foo])
        when(mockFooService.listFoos("prefix")).thenReturn(listCall)

        // Run the test
        def result = myClassUnderTest.listFoos("prefix")

        // Verify the results
    }

    @Test
    void testListFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response([]))

        // Run the test
        def result = myClassUnderTest.listFoos("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testListFoos_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.listFoos(...).
        def listCall = Calls.failure(new IOException("message"))
        when(mockFooService.listFoos("prefix")).thenReturn(listCall)

        // Run the test
        def result = myClassUnderTest.listFoos("prefix")

        // Verify the results
    }

    @Test
    void testSafeListFoos() {
        // Setup
        // Configure FooService.listFoos(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def listCall = Calls.response([foo])
        when(mockFooService.listFoos("prefix")).thenReturn(listCall)

        // Run the test
        def result = myClassUnderTest.safeListFoos("prefix")

        // Verify the results
    }

    @Test
    void testSafeListFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response([]))

        // Run the test
        def result = myClassUnderTest.safeListFoos("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testSafeListFoos_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.listFoos(...).
        def listCall = Calls.failure(new IOException("message"))
        when(mockFooService.listFoos("prefix")).thenReturn(listCall)

        // Run the test
        def result = myClassUnderTest.safeListFoos("prefix")

        // Verify the results
    }

    @Test
    void testGetFooWithId() {
        // Setup
        // Configure FooService.getFooWithId(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooCall = Calls.response(foo)
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall)

        // Run the test
        def result = myClassUnderTest.getFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testGetFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooWithId(...).
        def fooCall = Calls.failure(new IOException("message"))
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall)

        // Run the test
        def result = myClassUnderTest.getFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testSafeGetFooWithId() {
        // Setup
        // Configure FooService.getFooWithId(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooCall = Calls.response(foo)
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall)

        // Run the test
        def result = myClassUnderTest.safeGetFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testSafeGetFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooWithId(...).
        def fooCall = Calls.failure(new IOException("message"))
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall)

        // Run the test
        def result = myClassUnderTest.safeGetFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testGetOptionalFooWithId() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def optionalCall = Calls.response(Optional.of(foo))
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall)

        // Run the test
        def result = myClassUnderTest.getOptionalFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()))

        // Run the test
        def result = myClassUnderTest.getOptionalFooWithId("fooId")

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        def optionalCall = Calls.failure(new IOException("message"))
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall)

        // Run the test
        def result = myClassUnderTest.getOptionalFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testSafeGetOptionalFooWithId() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def optionalCall = Calls.response(Optional.of(foo))
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall)

        // Run the test
        def result = myClassUnderTest.safeGetOptionalFooWithId("fooId")

        // Verify the results
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()))

        // Run the test
        def result = myClassUnderTest.safeGetOptionalFooWithId("fooId")

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        def optionalCall = Calls.failure(new IOException("message"))
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall)

        // Run the test
        def result = myClassUnderTest.safeGetOptionalFooWithId("fooId")

        // Verify the results
    }
}
