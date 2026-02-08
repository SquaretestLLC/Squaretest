package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertFalse

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
