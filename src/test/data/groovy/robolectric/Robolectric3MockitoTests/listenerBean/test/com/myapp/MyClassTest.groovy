package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.mockito.Mockito.mock

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomething() {
        // Setup
        def mockFooListener = mock(FooListener.class)

        // Run the test
        myClassUnderTest.doSomething(mockFooListener)

        // Verify the results
    }
}
