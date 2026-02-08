package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testOtherBeanGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass();
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        myClassUnderTest.setConfigBean(configBean);
        myClassUnderTest.setFooService(null);
        final OtherBean otherBean = new OtherBean();
        myClassUnderTest.setOtherBean(otherBean);
        assertEquals(otherBean, myClassUnderTest.getOtherBean());
    }

    @Test
    public void testGetFooData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        myClassUnderTest.setConfigBean(configBean);
        myClassUnderTest.setFooService(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        myClassUnderTest.setConfigBean(configBean);
        myClassUnderTest.setFooService(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }
}
