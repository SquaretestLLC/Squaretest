package com.myapp

import com.myapp.foos.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
        jaxbListGetter.setFoo1Set(new HashSet<>([new Foo1("name", simpleBean)]))

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
