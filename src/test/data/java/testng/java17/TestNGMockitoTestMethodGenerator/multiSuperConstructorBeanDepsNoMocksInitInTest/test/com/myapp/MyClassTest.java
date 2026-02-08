package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testGetFooData11() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData31() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
    }

    @Test
    public void testGetOtherData1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        assertEquals("otherData", myClassUnderTest.getOtherData());
    }

    @Test
    public void testSetOtherData1() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        myClassUnderTest.setOtherData("otherData");

        // Verify the results
    }

    @Test
    public void testGetFooData21() {
        // Setup
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    public void testGetOtherBean1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        final OtherBean result = myClassUnderTest.getOtherBean();
    }

    @Test
    public void testSomeDataGetterAndSetter1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertEquals(someData, myClassUnderTest.getSomeData());
    }

    @Test
    public void testGetBaseId1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        assertEquals("BaseId", myClassUnderTest.getBaseId());
    }
}