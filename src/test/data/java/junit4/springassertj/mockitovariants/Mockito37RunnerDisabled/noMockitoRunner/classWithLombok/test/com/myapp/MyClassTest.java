package com.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
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
