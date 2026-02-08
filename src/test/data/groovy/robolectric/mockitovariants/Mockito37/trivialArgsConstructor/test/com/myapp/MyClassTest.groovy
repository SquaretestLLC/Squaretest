package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(["value"], ["value": "value"], new HashSet<>(["value"]))
    }

    @Test
    void testGetMapEntry() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"))
    }
}
