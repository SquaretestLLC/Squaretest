package com.myapp

import com.myapp.foos.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.time.LocalDateTime
import java.time.ZoneOffset

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomething() {
        // Setup
        def jaxbListGetter = new BeanWithJAXBListGetter()
        jaxbListGetter.getFavoriteQuotes().addAll(["value"])
        def beanWithOtherListGetters = new BeanWithOtherListGetters()
        beanWithOtherListGetters.getIs().addAll(["value"])
        beanWithOtherListGetters.getiSList().addAll(["value"])
        jaxbListGetter.getBeanWithOtherListGetters().addAll([beanWithOtherListGetters])
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        simpleBean.setMyOtherId(0L)
        simpleBean.setMyLastName("myLastName")
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        simpleBean.setMyAddressLine1("myAddressLine1")
        simpleBean.setMyAddressLine2("myAddressLine2")
        simpleBean.setMyZipCode("myZipCode")
        simpleBean.setMyCountry("myCountry")
        simpleBean.setMyCurrency(Currency.getInstance("USD"))
        jaxbListGetter.getFoo1Set().addAll(new HashSet<>([new Foo1("name", simpleBean)]))
        jaxbListGetter.setETag("eTag")
        jaxbListGetter.getETags().addAll(["value"])
        jaxbListGetter.getLAIDNumbers().addAll(["value"])
        jaxbListGetter.getSAIDNumbers().addAll(["value"])
        jaxbListGetter.getsAMNumbers().addAll(["value"])
        jaxbListGetter.getA().addAll(["value"])
        jaxbListGetter.getB().addAll(["value"])

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter)

        // Verify the results
    }

    @Test
    void testDoSomething1() {
        // Setup
        def beanWithListGetters2 = new BeanWithListGetters2()
        beanWithListGetters2.setIs(false)

        // Run the test
        myClassUnderTest.doSomething1(beanWithListGetters2)

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        def beanWithListGetters3 = new BeanWithListGetters3()
        beanWithListGetters3.getIs().addAll(["value"])

        // Run the test
        myClassUnderTest.doSomething2(beanWithListGetters3)

        // Verify the results
    }
}
