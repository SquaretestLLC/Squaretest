package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Set;

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
    void testSerialize1() {
        assertEquals("result", myClassUnderTest.serialize1(FooData.class, "input"));
    }

    @Test
    void testSerialize2() {
        assertEquals("result", myClassUnderTest.serialize2(Set.of(FooData.class), "input"));
    }

    @Test
    void testGetFoos1() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Set.of(FooData.class));

        // Run the test
        final Set<Class<? extends FooData>> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Set.of(FooData.class), result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptySet());

        // Run the test
        final Set<Class<? extends FooData>> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }
}
