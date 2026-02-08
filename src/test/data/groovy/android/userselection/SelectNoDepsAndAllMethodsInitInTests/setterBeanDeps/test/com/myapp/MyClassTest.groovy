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
    void testOtherBeanGetterAndSetter() {
        def myClassUnderTest = new MyClass()
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest.setConfigBean(configBean)
        myClassUnderTest.setFooService(null)
        def otherBean = new OtherBean()
        myClassUnderTest.setOtherBean(otherBean)
        assert otherBean == myClassUnderTest.getOtherBean()
    }

    @Test
    void testGetFooData1() {
        // Setup
        def myClassUnderTest = new MyClass()
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest.setConfigBean(configBean)
        myClassUnderTest.setFooService(null)

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        def myClassUnderTest = new MyClass()
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest.setConfigBean(configBean)
        myClassUnderTest.setFooService(null)

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }
}
