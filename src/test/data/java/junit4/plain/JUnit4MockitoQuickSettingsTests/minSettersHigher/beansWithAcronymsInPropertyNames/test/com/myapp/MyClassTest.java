package com.myapp;

import com.myapp.foos.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        simpleBean.setMyOtherId(0L);
        simpleBean.setMyLastName("myLastName");
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean.setMyAddressLine1("myAddressLine1");
        simpleBean.setMyAddressLine2("myAddressLine2");
        simpleBean.setMyZipCode("myZipCode");
        simpleBean.setMyCountry("myCountry");
        simpleBean.setMyCurrency(Currency.getInstance("USD"));
        jaxbListGetter.setFoo1Set(new HashSet<>(Arrays.asList(new Foo1("name", simpleBean))));
        jaxbListGetter.seteTag("eTag");
        jaxbListGetter.setETags(Arrays.asList("value"));
        jaxbListGetter.setLAIDNumbers(Arrays.asList("value"));
        jaxbListGetter.setsAIDNumbers(Arrays.asList("value"));
        jaxbListGetter.setSAMNumbers(Arrays.asList("value"));
        jaxbListGetter.setA(Arrays.asList("value"));
        jaxbListGetter.setB(Arrays.asList("value"));

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
