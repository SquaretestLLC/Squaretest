package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
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
