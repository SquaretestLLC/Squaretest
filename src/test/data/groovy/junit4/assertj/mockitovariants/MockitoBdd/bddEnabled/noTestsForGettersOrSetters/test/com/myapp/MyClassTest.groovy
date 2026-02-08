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
        myClassUnderTest = new MyClass("displayName", "canonicalName", ["value"], "canonicalText",
                "defaultInitExpression", false)
    }

    @Test
    void testGetDisplayName() {
        assertThat(myClassUnderTest.getDisplayName()).isEqualTo("displayName")
    }

    @Test
    void testGetCanonicalName() {
        assertThat(myClassUnderTest.getCanonicalName()).isEqualTo("canonicalName")
    }

    @Test
    void testGetCanonicalNamesRequiredForType() {
        assertThat(myClassUnderTest.getCanonicalNamesRequiredForType()).isEqualTo(["value"])
    }

    @Test
    void testGetCanonicalText() {
        assertThat(myClassUnderTest.getCanonicalText()).isEqualTo("canonicalText")
    }

    @Test
    void testGetDefaultInitExpression() {
        assertThat(myClassUnderTest.getDefaultInitExpression()).isEqualTo("defaultInitExpression")
    }

    @Test
    void testIsCanBeMocked() {
        assertThat(myClassUnderTest.isCanBeMocked()).isFalse()
    }
}
