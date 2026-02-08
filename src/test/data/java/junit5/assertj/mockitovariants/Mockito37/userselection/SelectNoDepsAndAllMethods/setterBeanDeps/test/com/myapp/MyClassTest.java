package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        myClassUnderTest.setConfigBean(configBean);
        myClassUnderTest.setFooService(null);
    }

    @Test
    void testOtherBeanGetterAndSetter() {
        final OtherBean otherBean = new OtherBean();
        myClassUnderTest.setOtherBean(otherBean);
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(otherBean);
    }

    @Test
    void testGetFooData1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }
}
