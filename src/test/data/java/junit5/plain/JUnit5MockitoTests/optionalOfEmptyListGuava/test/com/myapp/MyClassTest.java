package com.myapp;

import com.google.common.base.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    void testGetFoos1() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Optional.of(Arrays.asList("value")));

        // Run the test
        final Optional<List<String>> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Optional.of(Arrays.asList("value")), result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Optional.absent());

        // Run the test
        final Optional<List<String>> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Optional.absent(), result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<String>> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testMakeEmptyList1() {
        assertEquals(Optional.of(Collections.emptyList()), myClassUnderTest.makeEmptyList1());
    }

    @Test
    void testMakeEmptyList2() {
        assertEquals(Optional.of(Collections.emptyList()), myClassUnderTest.makeEmptyList2());
    }
}
