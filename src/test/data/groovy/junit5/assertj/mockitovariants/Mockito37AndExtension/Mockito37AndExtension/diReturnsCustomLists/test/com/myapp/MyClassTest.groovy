package com.myapp

import com.myapp.lists.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.assertj.core.api.Assertions.assertThat
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
    void testMakeListWithDelegateArgConstructor() {
        // Setup
        def expectedResult = new ListWithDelegateArgConstructor<>(["value"])

        // Configure FooService.makeListWithDelegateArgConstructor(...).
        def strings = new ListWithDelegateArgConstructor<>(["value"])
        when(mockFooService.makeListWithDelegateArgConstructor("key")).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.makeListWithDelegateArgConstructor("key")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(new ListWithDelegateArgConstructor<>(Collections.emptyList()))
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(new ListWithMultipleConstructorsIncludingNoArgs<>())
    }

    @Test
    void testMakeListWithNoConstructor() {
        // Setup
        def expectedResult = new ListWithNoConstructor<>()
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithNoConstructor("key")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testMakeListWithNoConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithNoConstructor("key")

        // Verify the results
        assertThat(result).isEqualTo(new ListWithNoConstructor<>())
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor() {
        // Setup
        def expectedResult = new ListWithOnlyNoArgsConstructor<>()
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>())

        // Run the test
        def result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key")

        // Verify the results
        assertThat(result).isEqualTo(new ListWithOnlyNoArgsConstructor<>())
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testMakeListWithEmptyStaticCreatorMethod_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithEmptyStaticCreatorMethod("key"))
                .thenReturn(ListWithEmptyStaticCreatorMethod.of())

        // Run the test
        def result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod("key")

        // Verify the results
        assertThat(result).isEqualTo(ListWithEmptyStaticCreatorMethod.of())
    }
}
