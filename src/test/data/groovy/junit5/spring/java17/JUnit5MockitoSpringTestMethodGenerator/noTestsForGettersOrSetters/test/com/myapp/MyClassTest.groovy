package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", ["value"], "canonicalText",
                "defaultInitExpression", false)
    }

    @Test
    void testGetDisplayName() {
        assert "displayName" == myClassUnderTest.getDisplayName()
    }

    @Test
    void testGetCanonicalName() {
        assert "canonicalName" == myClassUnderTest.getCanonicalName()
    }

    @Test
    void testGetCanonicalNamesRequiredForType() {
        assert ["value"] == myClassUnderTest.getCanonicalNamesRequiredForType()
    }

    @Test
    void testGetCanonicalText() {
        assert "canonicalText" == myClassUnderTest.getCanonicalText()
    }

    @Test
    void testGetDefaultInitExpression() {
        assert "defaultInitExpression" == myClassUnderTest.getDefaultInitExpression()
    }

    @Test
    void testIsCanBeMocked() {
        assertFalse(myClassUnderTest.isCanBeMocked())
    }
}