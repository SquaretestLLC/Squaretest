package com.myapp

import com.myapp.foos.BeanWithJAXBListGetter
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomething() {
        // Setup
        def jaxbListGetter = new BeanWithJAXBListGetter()
        jaxbListGetter.setName("name")
        jaxbListGetter.setAccountCreationDate(0L)
        jaxbListGetter.getFavoriteQuotes().addAll(["value"])
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        jaxbListGetter.getSimpleBeans().addAll([simpleBean])

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter)

        // Verify the results
    }
}
