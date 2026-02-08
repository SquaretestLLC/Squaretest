package com.myapp

import com.myapp.lists.*
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testMakeListWithDelegateArgConstructor() {
        // Setup
        def expectedResult = new ListWithDelegateArgConstructor<>(["value"])

        // Configure FooService.makeListWithDelegateArgConstructor(...).
        def strings = new ListWithDelegateArgConstructor<>(["value"])
        when(mockFooService.makeListWithDelegateArgConstructor("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithDelegateArgConstructor("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeListWithDelegateArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithDelegateArgConstructor(...).
        def strings = new ListWithDelegateArgConstructor<>(Collections.emptyList())
        when(mockFooService.makeListWithDelegateArgConstructor("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithDelegateArgConstructor("key")

        // Verify the results
        assert new ListWithDelegateArgConstructor<>(Collections.emptyList()) == result
    }

    @Test
    void testMakeListWithMultipleConstructorsIncludingNoArgs() {
        // Setup
        def expectedResult = new ListWithMultipleConstructorsIncludingNoArgs<>(["value"])

        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        def strings = new ListWithMultipleConstructorsIncludingNoArgs<>(["value"])
        when(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeListWithMultipleConstructorsIncludingNoArgs_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        def strings = new ListWithMultipleConstructorsIncludingNoArgs<>()
        when(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs("key")

        // Verify the results
        assert new ListWithMultipleConstructorsIncludingNoArgs<>() == result
    }

    @Test
    void testMakeListWithNoConstructor() {
        // Setup
        def expectedResult = new ListWithNoConstructor<>()
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithNoConstructor("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeListWithNoConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithNoConstructor("key")

        // Verify the results
        assert new ListWithNoConstructor<>() == result
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor() {
        // Setup
        def expectedResult = new ListWithOnlyNoArgsConstructor<>()
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key")

        // Verify the results
        assert new ListWithOnlyNoArgsConstructor<>() == result
    }

    @Test
    void testMakeListWithEmptyStaticCreatorMethod() {
        // Setup
        def expectedResult = ListWithEmptyStaticCreatorMethod.of("value")

        // Configure FooService.makeListWithEmptyStaticCreatorMethod(...).
        def strings = ListWithEmptyStaticCreatorMethod.of("value")
        when(mockFooService.makeListWithEmptyStaticCreatorMethod("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeListWithEmptyStaticCreatorMethod_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithEmptyStaticCreatorMethod("key"))
                .thenReturn(ListWithEmptyStaticCreatorMethod.of())

        // Run the test
        def result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod("key")

        // Verify the results
        assert ListWithEmptyStaticCreatorMethod.of() == result
    }
}
