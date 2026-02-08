package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetItems1() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetItems2() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetItems3() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData"))
                .thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetItems3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
