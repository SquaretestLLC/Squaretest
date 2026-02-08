package com.myapp;

import com.myapp.foos.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething() {
        // Setup
        final BeanWithJAXBListGetter jaxbListGetter = new BeanWithJAXBListGetter();
        jaxbListGetter.getFavoriteQuotes().addAll(Arrays.asList("value"));
        final BeanWithOtherListGetters beanWithOtherListGetters = new BeanWithOtherListGetters();
        beanWithOtherListGetters.getIs().addAll(Arrays.asList("value"));
        beanWithOtherListGetters.getiSList().addAll(Arrays.asList("value"));
        jaxbListGetter.getBeanWithOtherListGetters().addAll(Arrays.asList(beanWithOtherListGetters));
        final SimpleBean simpleBean = new SimpleBean();
        jaxbListGetter.getFoo1Set().addAll(new HashSet<>(Arrays.asList(new Foo1("name", simpleBean))));

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter);

        // Verify the results
    }

    @Test
    public void testDoSomething1() {
        // Setup
        final BeanWithListGetters2 beanWithListGetters2 = new BeanWithListGetters2();
        beanWithListGetters2.setIs(false);

        // Run the test
        myClassUnderTest.doSomething1(beanWithListGetters2);

        // Verify the results
    }

    @Test
    public void testDoSomething2() {
        // Setup
        final BeanWithListGetters3 beanWithListGetters3 = new BeanWithListGetters3();
        beanWithListGetters3.getIs().addAll(Arrays.asList("value"));

        // Run the test
        myClassUnderTest.doSomething2(beanWithListGetters3);

        // Verify the results
    }
}
