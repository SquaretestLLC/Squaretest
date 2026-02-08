package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
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
}