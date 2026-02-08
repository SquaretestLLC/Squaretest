package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(["value"], ["value": "value"], new HashSet<>(["value"]))
    }

    @Test
    void testGetMapEntry() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"))
    }
}
