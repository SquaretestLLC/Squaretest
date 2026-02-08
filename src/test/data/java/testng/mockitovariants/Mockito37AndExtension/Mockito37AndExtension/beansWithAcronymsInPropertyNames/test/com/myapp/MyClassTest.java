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
        final BeanWithAcronymsInPropertyNames jaxbListGetter = new BeanWithAcronymsInPropertyNames();
        jaxbListGetter.setFavoriteQuotes(Arrays.asList("value"));
        final BeanWithOtherListGetters beanWithOtherListGetters = new BeanWithOtherListGetters();
        beanWithOtherListGetters.setIs(Arrays.asList("value"));
        beanWithOtherListGetters.setiSList(Arrays.asList("value"));
        jaxbListGetter.setBeanWithOtherListGetters(Arrays.asList(beanWithOtherListGetters));
        final SimpleBean simpleBean = new SimpleBean();
        jaxbListGetter.setFoo1Set(new HashSet<>(Arrays.asList(new Foo1("name", simpleBean))));

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter);

        // Verify the results
    }

    @Test
    public void testDoSomething1() {
        // Setup
        final BeanWithAcronyms2 beanWithAcronyms2 = new BeanWithAcronyms2();
        beanWithAcronyms2.setIs(false);

        // Run the test
        myClassUnderTest.doSomething1(beanWithAcronyms2);

        // Verify the results
    }

    @Test
    public void testDoSomething2() {
        // Setup
        final BeanWithAcronyms3 beanWithAcronyms3 = new BeanWithAcronyms3();
        beanWithAcronyms3.setIs(Arrays.asList("value"));

        // Run the test
        myClassUnderTest.doSomething2(beanWithAcronyms3);

        // Verify the results
    }
}
