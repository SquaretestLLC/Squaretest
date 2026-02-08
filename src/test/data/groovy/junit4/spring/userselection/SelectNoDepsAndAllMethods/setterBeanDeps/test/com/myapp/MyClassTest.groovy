package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest.setConfigBean(configBean)
        myClassUnderTest.setFooService(null)
    }

    @Test
    void testOtherBeanGetterAndSetter() {
        def otherBean = new OtherBean()
        myClassUnderTest.setOtherBean(otherBean)
        assert otherBean == myClassUnderTest.getOtherBean()
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
}
