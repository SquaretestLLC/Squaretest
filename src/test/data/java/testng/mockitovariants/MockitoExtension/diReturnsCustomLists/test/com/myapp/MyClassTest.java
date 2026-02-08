package com.myapp;

import com.myapp.lists.*;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

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
    public void testMakeListWithDelegateArgConstructor() {
        // Setup
        final ListWithDelegateArgConstructor<String> expectedResult = new ListWithDelegateArgConstructor<>(
                Arrays.asList("value"));

        // Configure FooService.makeListWithDelegateArgConstructor(...).
        final ListWithDelegateArgConstructor<String> strings = new ListWithDelegateArgConstructor<>(
                Arrays.asList("value"));
        when(mockFooService.makeListWithDelegateArgConstructor("key")).thenReturn(strings);

        // Run the test
        final ListWithDelegateArgConstructor<String> result = myClassUnderTest.makeListWithDelegateArgConstructor(
                "key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeListWithDelegateArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithDelegateArgConstructor(...).
        final ListWithDelegateArgConstructor<String> strings = new ListWithDelegateArgConstructor<>(
                Collections.emptyList());
        when(mockFooService.makeListWithDelegateArgConstructor("key")).thenReturn(strings);

        // Run the test
        final ListWithDelegateArgConstructor<String> result = myClassUnderTest.makeListWithDelegateArgConstructor(
                "key");

        // Verify the results
        assertEquals(new ListWithDelegateArgConstructor<>(Collections.emptyList()), result);
    }

    @Test
    public void testMakeListWithMultipleConstructorsIncludingNoArgs() {
        // Setup
        final ListWithMultipleConstructorsIncludingNoArgs<String> expectedResult = new ListWithMultipleConstructorsIncludingNoArgs<>(
                Arrays.asList("value"));

        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        final ListWithMultipleConstructorsIncludingNoArgs<String> strings = new ListWithMultipleConstructorsIncludingNoArgs<>(
                Arrays.asList("value"));
        when(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).thenReturn(strings);

        // Run the test
        final ListWithMultipleConstructorsIncludingNoArgs<String> result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs(
                "key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeListWithMultipleConstructorsIncludingNoArgs_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        final ListWithMultipleConstructorsIncludingNoArgs<String> strings = new ListWithMultipleConstructorsIncludingNoArgs<>();
        when(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).thenReturn(strings);

        // Run the test
        final ListWithMultipleConstructorsIncludingNoArgs<String> result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs(
                "key");

        // Verify the results
        assertEquals(new ListWithMultipleConstructorsIncludingNoArgs<>(), result);
    }

    @Test
    public void testMakeListWithNoConstructor() {
        // Setup
        final ListWithNoConstructor<String> expectedResult = new ListWithNoConstructor<>();
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>());

        // Run the test
        final ListWithNoConstructor<String> result = myClassUnderTest.makeListWithNoConstructor("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeListWithNoConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithNoConstructor("key")).thenReturn(new ListWithNoConstructor<>());

        // Run the test
        final ListWithNoConstructor<String> result = myClassUnderTest.makeListWithNoConstructor("key");

        // Verify the results
        assertEquals(new ListWithNoConstructor<>(), result);
    }

    @Test
    public void testMakeListWithOnlyNoArgsConstructor() {
        // Setup
        final ListWithOnlyNoArgsConstructor<String> expectedResult = new ListWithOnlyNoArgsConstructor<>();
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>());

        // Run the test
        final ListWithOnlyNoArgsConstructor<String> result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeListWithOnlyNoArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithOnlyNoArgsConstructor("key")).thenReturn(new ListWithOnlyNoArgsConstructor<>());

        // Run the test
        final ListWithOnlyNoArgsConstructor<String> result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key");

        // Verify the results
        assertEquals(new ListWithOnlyNoArgsConstructor<>(), result);
    }

    @Test
    public void testMakeListWithEmptyStaticCreatorMethod() {
        // Setup
        final ListWithEmptyStaticCreatorMethod<String> expectedResult = ListWithEmptyStaticCreatorMethod.of("value");

        // Configure FooService.makeListWithEmptyStaticCreatorMethod(...).
        final ListWithEmptyStaticCreatorMethod<String> strings = ListWithEmptyStaticCreatorMethod.of("value");
        when(mockFooService.makeListWithEmptyStaticCreatorMethod("key")).thenReturn(strings);

        // Run the test
        final ListWithEmptyStaticCreatorMethod<String> result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod(
                "key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeListWithEmptyStaticCreatorMethod_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.makeListWithEmptyStaticCreatorMethod("key"))
                .thenReturn(ListWithEmptyStaticCreatorMethod.of());

        // Run the test
        final ListWithEmptyStaticCreatorMethod<String> result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod(
                "key");

        // Verify the results
        assertEquals(ListWithEmptyStaticCreatorMethod.of(), result);
    }
}
