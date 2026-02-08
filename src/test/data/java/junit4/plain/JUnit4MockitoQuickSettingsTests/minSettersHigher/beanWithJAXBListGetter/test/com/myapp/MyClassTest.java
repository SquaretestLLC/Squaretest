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
        final BeanWithJAXBListGetter jaxbListGetter = new BeanWithJAXBListGetter();
        jaxbListGetter.getFavoriteQuotes().addAll(Arrays.asList("value"));
        final BeanWithOtherListGetters beanWithOtherListGetters = new BeanWithOtherListGetters();
        beanWithOtherListGetters.getIs().addAll(Arrays.asList("value"));
        beanWithOtherListGetters.getiSList().addAll(Arrays.asList("value"));
        jaxbListGetter.getBeanWithOtherListGetters().addAll(Arrays.asList(beanWithOtherListGetters));
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
        jaxbListGetter.getFoo1Set().addAll(new HashSet<>(Arrays.asList(new Foo1("name", simpleBean))));
        jaxbListGetter.setETag("eTag");
        jaxbListGetter.getETags().addAll(Arrays.asList("value"));
        jaxbListGetter.getLAIDNumbers().addAll(Arrays.asList("value"));
        jaxbListGetter.getSAIDNumbers().addAll(Arrays.asList("value"));
        jaxbListGetter.getsAMNumbers().addAll(Arrays.asList("value"));
        jaxbListGetter.getA().addAll(Arrays.asList("value"));
        jaxbListGetter.getB().addAll(Arrays.asList("value"));

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
