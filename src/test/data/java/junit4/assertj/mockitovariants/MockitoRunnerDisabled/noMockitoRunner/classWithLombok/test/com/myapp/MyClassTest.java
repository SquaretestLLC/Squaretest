package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testGetFooData() {
        // Setup
        // Configure Foo.createFooData(...).
        final FooData fooData = new FooData();
        when(mockFoo.createFooData()).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData();

        // Verify the results
    }
}
