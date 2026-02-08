package com.myapp;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testCreateRequest() {
        // Setup
        final AnnotateImageRequest expectedResult = AnnotateImageRequest.newBuilder().build();

        // Run the test
        final AnnotateImageRequest result = myClassUnderTest.createRequest();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
