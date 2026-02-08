package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooData1() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData3() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        assert "otherData" == myClassUnderTest.getOtherData()
    }

    @Test
    void testSetOtherData() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        def result = myClassUnderTest.getOtherBean()
    }

    @Test
    void testSomeDataGetterAndSetter() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assert someData == myClassUnderTest.getSomeData()
    }
}
