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
        configBean.setThirdPath("thirdPath")
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
    void testGetFooData3() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        assert "otherData" == myClassUnderTest.getOtherData()
    }

    @Test
    void testSetOtherData() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData")

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

    @Test
    void testSomeDataGetterAndSetter() {
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assert someData == myClassUnderTest.getSomeData()
    }

    @Test
    void testGetBaseId() {
        assert "BaseId" == myClassUnderTest.getBaseId()
    }
}
