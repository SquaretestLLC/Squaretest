package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.foos.BeanWithJAXBListGetter
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
