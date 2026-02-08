package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testGetFooData11() {
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
    void testGetFooData31() {
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
    void testGetOtherData1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData");
    }

    @Test
    void testSetOtherData1() {
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
    void testGetFooData21() {
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
    void testGetOtherBean1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        final OtherBean result = myClassUnderTest.getOtherBean();
    }

    @Test
    void testSomeDataGetterAndSetter1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData);
    }

    @Test
    void testGetBaseId1() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        configBean.setThirdPath("thirdPath");
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
        assertThat(myClassUnderTest.getBaseId()).isEqualTo("BaseId");
    }
}