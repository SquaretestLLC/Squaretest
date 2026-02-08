package com.myapp;

import com.myapp.annotationdto.FooProvider;
import com.myapp.annotationdto.FooWithDataAnnotationMultiConstructors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    void testGetTheData() {
        // Setup
        // Configure FooProvider.getTheData(...).
        final FooWithDataAnnotationMultiConstructors fooWithDataAnnotationMultiConstructors = new FooWithDataAnnotationMultiConstructors();
        when(mockFooProvider.getTheData("id")).thenReturn(fooWithDataAnnotationMultiConstructors);

        // Run the test
        final FooWithDataAnnotationMultiConstructors result = myClassUnderTest.getTheData("id");

        // Verify the results
    }
}
