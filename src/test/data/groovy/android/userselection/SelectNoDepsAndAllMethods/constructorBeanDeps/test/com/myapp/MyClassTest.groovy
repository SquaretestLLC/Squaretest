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
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
    }

    @Test
    void testGetFooData1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        def result = myClassUnderTest.getOtherBean()
    }
}
