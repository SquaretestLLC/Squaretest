package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockFooService);
    }

    @Test
    void testGetItems11() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn(List.of("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(List.of("value"));
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetItems21() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn(List.of("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(List.of("value"));
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}