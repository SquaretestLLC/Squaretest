package com.myapp

import com.myapp.foos.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDateTime
import java.time.ZoneOffset

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomething() {
        // Setup
        def jaxbListGetter = new BeanWithAcronymsInPropertyNames()
        jaxbListGetter.setFavoriteQuotes(["value"])
        def beanWithOtherListGetters = new BeanWithOtherListGetters()
        beanWithOtherListGetters.setIs(["value"])
        beanWithOtherListGetters.setiSList(["value"])
        jaxbListGetter.setBeanWithOtherListGetters([beanWithOtherListGetters])
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
        jaxbListGetter.setFoo1Set(new HashSet<>([new Foo1("name", simpleBean)]))
        jaxbListGetter.seteTag("eTag")
        jaxbListGetter.setETags(["value"])
        jaxbListGetter.setLAIDNumbers(["value"])
        jaxbListGetter.setsAIDNumbers(["value"])
        jaxbListGetter.setSAMNumbers(["value"])
        jaxbListGetter.setA(["value"])
        jaxbListGetter.setB(["value"])

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter)

        // Verify the results
    }

    @Test
    void testDoSomething1() {
        // Setup
        def beanWithAcronyms2 = new BeanWithAcronyms2()
        beanWithAcronyms2.setIs(false)

        // Run the test
        myClassUnderTest.doSomething1(beanWithAcronyms2)

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        def beanWithAcronyms3 = new BeanWithAcronyms3()
        beanWithAcronyms3.setIs(["value"])

        // Run the test
        myClassUnderTest.doSomething2(beanWithAcronyms3)

        // Verify the results
    }
}
