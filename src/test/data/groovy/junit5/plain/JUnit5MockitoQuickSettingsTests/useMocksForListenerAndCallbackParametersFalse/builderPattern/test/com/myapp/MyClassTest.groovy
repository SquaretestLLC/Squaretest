package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = null
    }

    @Test
    void testGetFullName() {
        assert "result" == myClassUnderTest.getFullName()
    }

    @Test
    void testGetFullNameWithSuffix() {
        assert "result" == myClassUnderTest.getFullNameWithSuffix()
    }
}
