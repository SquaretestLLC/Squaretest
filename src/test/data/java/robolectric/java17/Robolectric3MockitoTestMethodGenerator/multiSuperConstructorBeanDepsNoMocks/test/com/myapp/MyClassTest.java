package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
    }

    @Test
    public void testGetFooData11() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData31() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
    }

    @Test
    public void testGetOtherData1() {
        assertEquals("otherData", myClassUnderTest.getOtherData());
    }

    @Test
    public void testSetOtherData1() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData");

        // Verify the results
    }

    @Test
    public void testGetFooData21() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    public void testGetOtherBean1() {
        final OtherBean result = myClassUnderTest.getOtherBean();
    }

    @Test
    public void testSomeDataGetterAndSetter1() {
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertEquals(someData, myClassUnderTest.getSomeData());
    }

    @Test
    public void testGetBaseId1() {
        assertEquals("BaseId", myClassUnderTest.getBaseId());
    }
}