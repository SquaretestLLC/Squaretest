package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
