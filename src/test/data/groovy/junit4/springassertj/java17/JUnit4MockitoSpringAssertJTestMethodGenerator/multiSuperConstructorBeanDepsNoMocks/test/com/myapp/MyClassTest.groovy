package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
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
    void testGetFooData11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData31() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData1() {
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData")
    }

    @Test
    void testSetOtherData1() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData21() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean1() {
        def result = myClassUnderTest.getOtherBean()
    }

    @Test
    void testSomeDataGetterAndSetter1() {
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData)
    }

    @Test
    void testGetBaseId1() {
        assertThat(myClassUnderTest.getBaseId()).isEqualTo("BaseId")
    }
}