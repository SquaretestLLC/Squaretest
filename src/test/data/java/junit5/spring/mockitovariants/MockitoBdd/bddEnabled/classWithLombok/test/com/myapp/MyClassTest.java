package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure Foo.createFooData(...).
        final FooData fooData = new FooData();
        given(mockFoo.createFooData()).willReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData();

        // Verify the results
    }
}
