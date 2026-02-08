package com.myapp;

import com.myapp.lists.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testMakeListWithDelegateArgConstructor() {
        // Setup
        final ListWithDelegateArgConstructor<String> expectedResult = new ListWithDelegateArgConstructor<>(
                Arrays.asList("value"));

        // Configure FooService.makeListWithDelegateArgConstructor(...).
        final ListWithDelegateArgConstructor<String> strings = new ListWithDelegateArgConstructor<>(
                Arrays.asList("value"));
        given(mockFooService.makeListWithDelegateArgConstructor("key")).willReturn(strings);

        // Run the test
        final ListWithDelegateArgConstructor<String> result = myClassUnderTest.makeListWithDelegateArgConstructor(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeListWithDelegateArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithDelegateArgConstructor(...).
        final ListWithDelegateArgConstructor<String> strings = new ListWithDelegateArgConstructor<>(
                Collections.emptyList());
        given(mockFooService.makeListWithDelegateArgConstructor("key")).willReturn(strings);

        // Run the test
        final ListWithDelegateArgConstructor<String> result = myClassUnderTest.makeListWithDelegateArgConstructor(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(new ListWithDelegateArgConstructor<>(Collections.emptyList()));
    }

    @Test
    void testMakeListWithMultipleConstructorsIncludingNoArgs() {
        // Setup
        final ListWithMultipleConstructorsIncludingNoArgs<String> expectedResult = new ListWithMultipleConstructorsIncludingNoArgs<>(
                Arrays.asList("value"));

        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        final ListWithMultipleConstructorsIncludingNoArgs<String> strings = new ListWithMultipleConstructorsIncludingNoArgs<>(
                Arrays.asList("value"));
        given(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).willReturn(strings);

        // Run the test
        final ListWithMultipleConstructorsIncludingNoArgs<String> result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeListWithMultipleConstructorsIncludingNoArgs_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.makeListWithMultipleConstructorsIncludingNoArgs(...).
        final ListWithMultipleConstructorsIncludingNoArgs<String> strings = new ListWithMultipleConstructorsIncludingNoArgs<>();
        given(mockFooService.makeListWithMultipleConstructorsIncludingNoArgs("key")).willReturn(strings);

        // Run the test
        final ListWithMultipleConstructorsIncludingNoArgs<String> result = myClassUnderTest.makeListWithMultipleConstructorsIncludingNoArgs(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(new ListWithMultipleConstructorsIncludingNoArgs<>());
    }

    @Test
    void testMakeListWithNoConstructor() {
        // Setup
        final ListWithNoConstructor<String> expectedResult = new ListWithNoConstructor<>();
        given(mockFooService.makeListWithNoConstructor("key")).willReturn(new ListWithNoConstructor<>());

        // Run the test
        final ListWithNoConstructor<String> result = myClassUnderTest.makeListWithNoConstructor("key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeListWithNoConstructor_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.makeListWithNoConstructor("key")).willReturn(new ListWithNoConstructor<>());

        // Run the test
        final ListWithNoConstructor<String> result = myClassUnderTest.makeListWithNoConstructor("key");

        // Verify the results
        assertThat(result).isEqualTo(new ListWithNoConstructor<>());
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor() {
        // Setup
        final ListWithOnlyNoArgsConstructor<String> expectedResult = new ListWithOnlyNoArgsConstructor<>();
        given(mockFooService.makeListWithOnlyNoArgsConstructor("key"))
                .willReturn(new ListWithOnlyNoArgsConstructor<>());

        // Run the test
        final ListWithOnlyNoArgsConstructor<String> result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeListWithOnlyNoArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.makeListWithOnlyNoArgsConstructor("key"))
                .willReturn(new ListWithOnlyNoArgsConstructor<>());

        // Run the test
        final ListWithOnlyNoArgsConstructor<String> result = myClassUnderTest.makeListWithOnlyNoArgsConstructor("key");

        // Verify the results
        assertThat(result).isEqualTo(new ListWithOnlyNoArgsConstructor<>());
    }

    @Test
    void testMakeListWithEmptyStaticCreatorMethod() {
        // Setup
        final ListWithEmptyStaticCreatorMethod<String> expectedResult = ListWithEmptyStaticCreatorMethod.of("value");

        // Configure FooService.makeListWithEmptyStaticCreatorMethod(...).
        final ListWithEmptyStaticCreatorMethod<String> strings = ListWithEmptyStaticCreatorMethod.of("value");
        given(mockFooService.makeListWithEmptyStaticCreatorMethod("key")).willReturn(strings);

        // Run the test
        final ListWithEmptyStaticCreatorMethod<String> result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeListWithEmptyStaticCreatorMethod_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.makeListWithEmptyStaticCreatorMethod("key"))
                .willReturn(ListWithEmptyStaticCreatorMethod.of());

        // Run the test
        final ListWithEmptyStaticCreatorMethod<String> result = myClassUnderTest.makeListWithEmptyStaticCreatorMethod(
                "key");

        // Verify the results
        assertThat(result).isEqualTo(ListWithEmptyStaticCreatorMethod.of());
    }
}
