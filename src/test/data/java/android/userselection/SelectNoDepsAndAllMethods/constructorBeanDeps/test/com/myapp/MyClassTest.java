package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setBasePath("basePath");
        configBean.setSpecialPath("specialPath");
        myClassUnderTest = new MyClass(configBean, new OtherBean(), null);
    }

    @Test
    public void testGetFooData1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData2() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    public void testGetOtherBean() {
        final OtherBean result = myClassUnderTest.getOtherBean();
    }
}
