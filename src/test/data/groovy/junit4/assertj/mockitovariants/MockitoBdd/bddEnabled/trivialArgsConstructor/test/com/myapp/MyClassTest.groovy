package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(["value"], ["value": "value"], new HashSet<>(["value"]))
    }

    @Test
    void testGetMapEntry() {
        assertThat(myClassUnderTest.getMapEntry("customerKey")).isNull()
    }
}
