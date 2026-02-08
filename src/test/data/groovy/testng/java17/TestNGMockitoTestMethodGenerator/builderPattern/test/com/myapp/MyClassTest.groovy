package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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