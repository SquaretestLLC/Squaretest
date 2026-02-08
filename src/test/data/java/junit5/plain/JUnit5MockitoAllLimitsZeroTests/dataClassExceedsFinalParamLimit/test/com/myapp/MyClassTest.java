package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
    void testGetBigData1() {
        // Setup
        when(mockFooService.getBigData1("id")).thenReturn(null);

        // Run the test
        final BigDataNoOptions result = myClassUnderTest.getBigData1("id");

        // Verify the results
    }

    @Test
    void testGetBigData2() {
        // Setup
        when(mockFooService.getBigData2("id")).thenReturn(Arrays.asList());

        // Run the test
        final List<BigDataNoOptions> result = myClassUnderTest.getBigData2("id");

        // Verify the results
    }

    @Test
    void testGetBigData2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getBigData2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<BigDataNoOptions> result = myClassUnderTest.getBigData2("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetBigData3() {
        // Setup
        when(mockFooService.getBigData3("id")).thenReturn(Optional.of(null));

        // Run the test
        final Optional<BigDataNoOptions> result = myClassUnderTest.getBigData3("id");

        // Verify the results
    }

    @Test
    void testGetBigData3_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getBigData3("id")).thenReturn(Optional.empty());

        // Run the test
        final Optional<BigDataNoOptions> result = myClassUnderTest.getBigData3("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetBigData4() {
        // Setup
        when(mockFooService.getBigData4("id")).thenReturn(Optional.of(Arrays.asList()));

        // Run the test
        final Optional<List<BigDataNoOptions>> result = myClassUnderTest.getBigData4("id");

        // Verify the results
    }

    @Test
    void testGetBigData4_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getBigData4("id")).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<BigDataNoOptions>> result = myClassUnderTest.getBigData4("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetBigData4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getBigData4("id")).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<BigDataNoOptions>> result = myClassUnderTest.getBigData4("id");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testGetBigData5() {
        // Setup
        when(mockFooService.getBigData5("id")).thenReturn(BigDataOtherOption.builder().build());

        // Run the test
        final BigDataOtherOption result = myClassUnderTest.getBigData5("id");

        // Verify the results
    }

    @Test
    void testGetBigData6() {
        // Setup
        // Configure FooService.getBigData6(...).
        final List<BigDataOtherOption> bigDataOtherOptions = Arrays.asList(BigDataOtherOption.builder().build());
        when(mockFooService.getBigData6("id")).thenReturn(bigDataOtherOptions);

        // Run the test
        final List<BigDataOtherOption> result = myClassUnderTest.getBigData6("id");

        // Verify the results
    }

    @Test
    void testGetBigData6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getBigData6("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<BigDataOtherOption> result = myClassUnderTest.getBigData6("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetBigData7() {
        // Setup
        // Configure FooService.getBigData7(...).
        final Optional<BigDataOtherOption> bigDataOtherOption = Optional.of(BigDataOtherOption.builder().build());
        when(mockFooService.getBigData7("id")).thenReturn(bigDataOtherOption);

        // Run the test
        final Optional<BigDataOtherOption> result = myClassUnderTest.getBigData7("id");

        // Verify the results
    }

    @Test
    void testGetBigData7_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getBigData7("id")).thenReturn(Optional.empty());

        // Run the test
        final Optional<BigDataOtherOption> result = myClassUnderTest.getBigData7("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetBigData8() {
        // Setup
        // Configure FooService.getBigData8(...).
        final Optional<List<BigDataOtherOption>> bigDataOtherOptions = Optional.of(
                Arrays.asList(BigDataOtherOption.builder().build()));
        when(mockFooService.getBigData8("id")).thenReturn(bigDataOtherOptions);

        // Run the test
        final Optional<List<BigDataOtherOption>> result = myClassUnderTest.getBigData8("id");

        // Verify the results
    }

    @Test
    void testGetBigData8_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getBigData8("id")).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<BigDataOtherOption>> result = myClassUnderTest.getBigData8("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetBigData8_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getBigData8("id")).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<BigDataOtherOption>> result = myClassUnderTest.getBigData8("id");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testGetBigData9() {
        // Setup
        when(mockFooService.getBigData9("id")).thenReturn(CompletableFuture.completedFuture(null));

        // Run the test
        final CompletableFuture<BigDataNoOptions> result = myClassUnderTest.getBigData9("id");

        // Verify the results
    }

    @Test
    void testGetBigData9_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getBigData9(...).
        final CompletableFuture<BigDataNoOptions> bigDataNoOptionsCompletableFuture = new CompletableFuture<>();
        bigDataNoOptionsCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getBigData9("id")).thenReturn(bigDataNoOptionsCompletableFuture);

        // Run the test
        final CompletableFuture<BigDataNoOptions> result = myClassUnderTest.getBigData9("id");

        // Verify the results
    }

    @Test
    void testGetBigData10() {
        // Setup
        // Configure FooService.getBigData10(...).
        final CompletableFuture<List<BigDataNoOptions>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList());
        when(mockFooService.getBigData10("id")).thenReturn(listCompletableFuture);

        // Run the test
        final CompletableFuture<List<BigDataNoOptions>> result = myClassUnderTest.getBigData10("id");

        // Verify the results
    }

    @Test
    void testGetBigData10_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getBigData10(...).
        final CompletableFuture<List<BigDataNoOptions>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockFooService.getBigData10("id")).thenReturn(listCompletableFuture);

        // Run the test
        final CompletableFuture<List<BigDataNoOptions>> result = myClassUnderTest.getBigData10("id");

        // Verify the results
    }

    @Test
    void testGetBigData10_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getBigData10(...).
        final CompletableFuture<List<BigDataNoOptions>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getBigData10("id")).thenReturn(listCompletableFuture);

        // Run the test
        final CompletableFuture<List<BigDataNoOptions>> result = myClassUnderTest.getBigData10("id");

        // Verify the results
    }
}
