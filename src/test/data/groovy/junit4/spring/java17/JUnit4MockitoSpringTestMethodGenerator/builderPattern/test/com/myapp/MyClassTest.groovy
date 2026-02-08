package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = null
    }

    @Test
    void testGetFullName1() {
        assert "result" == myClassUnderTest.getFullName()
    }

    @Test
    void testGetFullNameWithSuffix1() {
        assert "result" == myClassUnderTest.getFullNameWithSuffix()
    }
}