package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
    }

    @Test
    void testGetFooData1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    void testGetFooData3() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        assertEquals("otherData", myClassUnderTest.getOtherData());
    }

    @Test
    void testSetOtherData() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData");

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        final OtherBean result = myClassUnderTest.getOtherBean();
    }

    @Test
    void testSomeDataGetterAndSetter() {
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertEquals(someData, myClassUnderTest.getSomeData());
    }

    @Test
    void testGetBaseId() {
        assertEquals("BaseId", myClassUnderTest.getBaseId());
    }
}
