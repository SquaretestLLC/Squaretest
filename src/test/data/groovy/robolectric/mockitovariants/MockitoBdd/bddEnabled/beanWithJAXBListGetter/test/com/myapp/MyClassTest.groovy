package com.myapp

import com.myapp.foos.BeanWithJAXBListGetter
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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
