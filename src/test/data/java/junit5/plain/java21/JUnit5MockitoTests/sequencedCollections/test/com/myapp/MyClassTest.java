package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SequencedCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoo1("id"));
    }

    @Test
    void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoo3("id"));
    }

    @Test
    void testGetFoo4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoo4("id"));
    }

    @Test
    void testGetFoos1() {
        // Setup
        final SequencedCollection<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final SequencedCollection<FooData> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final SequencedCollection<FooData> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos2() {
        // Setup
        final SequencedCollection<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockFooService.getFoos1("id")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final SequencedCollection<FooData> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final SequencedCollection<FooData> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
