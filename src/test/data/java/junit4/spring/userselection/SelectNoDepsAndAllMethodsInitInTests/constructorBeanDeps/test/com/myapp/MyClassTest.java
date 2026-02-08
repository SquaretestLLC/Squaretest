package com.myapp;

import org.junit.Test;

public class MyClassTest {

    @Test
    public void testGetFooData1() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData2() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    public void testGetOtherBean() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        final OtherBean result = myClassUnderTest.getOtherBean();
    }
}
