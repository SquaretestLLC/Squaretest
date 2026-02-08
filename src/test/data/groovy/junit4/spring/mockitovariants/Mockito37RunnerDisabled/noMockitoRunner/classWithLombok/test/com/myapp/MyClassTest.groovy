package com.myapp

import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure Foo.createFooData(...).
        def fooData = new FooData()
        when(mockFoo.createFooData()).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData()

        // Verify the results
    }
}
