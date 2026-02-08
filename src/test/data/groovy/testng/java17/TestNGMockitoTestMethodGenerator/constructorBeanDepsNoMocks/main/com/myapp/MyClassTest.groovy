package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
    }

    @Test
    void testGetFooData1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        final OtherBean result = myClassUnderTest.getOtherBean()
    }
}
