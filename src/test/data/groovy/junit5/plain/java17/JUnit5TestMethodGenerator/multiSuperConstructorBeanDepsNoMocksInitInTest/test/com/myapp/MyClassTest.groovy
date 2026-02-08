package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooData11() {
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
    void testGetFooData31() {
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
    void testGetOtherData1() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        assert "otherData" == myClassUnderTest.getOtherData()
    }

    @Test
    void testSetOtherData1() {
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
    void testGetFooData21() {
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
    void testGetOtherBean1() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        def result = myClassUnderTest.getOtherBean()
    }

    @Test
    void testSomeDataGetterAndSetter1() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assert someData == myClassUnderTest.getSomeData()
    }

    @Test
    void testGetBaseId1() {
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        def myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        assert "BaseId" == myClassUnderTest.getBaseId()
    }
}