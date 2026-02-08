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
        myClassUnderTest = null
    }

    @Test
    void testGetFullName1() {
        assertThat(myClassUnderTest.getFullName()).isEqualTo("result")
    }

    @Test
    void testGetFullNameWithSuffix1() {
        assertThat(myClassUnderTest.getFullNameWithSuffix()).isEqualTo("result")
    }
}