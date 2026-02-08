package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNull

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(["value"], ["value": "value"], new HashSet<>(["value"]))
    }

    @Test
    void testGetMapEntry() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"))
    }
}
