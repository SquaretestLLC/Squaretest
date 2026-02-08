package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.mockito.Mockito.mock

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
