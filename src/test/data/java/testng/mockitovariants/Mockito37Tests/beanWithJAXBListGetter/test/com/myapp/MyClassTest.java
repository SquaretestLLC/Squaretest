package com.myapp;

import com.myapp.foos.BeanWithJAXBListGetter;
import com.myapp.foos.SimpleBean;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

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
        jaxbListGetter.setName("name");
        jaxbListGetter.setAccountCreationDate(0L);
        jaxbListGetter.getFavoriteQuotes().addAll(Arrays.asList("value"));
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        jaxbListGetter.getSimpleBeans().addAll(Arrays.asList(simpleBean));

        // Run the test
        myClassUnderTest.doSomething(jaxbListGetter);

        // Verify the results
    }
}
