package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
