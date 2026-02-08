package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = MyClass.getInstance()
    }

    @Test
    void testGetInstance1() {
        // Setup
        // Run the test
        def result = MyClass.getInstance()

        // Verify the results
    }

    @Test
    void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}