package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testGetFooData11() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData21() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean1() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        def result = myClassUnderTest.getOtherBean()
    }
}