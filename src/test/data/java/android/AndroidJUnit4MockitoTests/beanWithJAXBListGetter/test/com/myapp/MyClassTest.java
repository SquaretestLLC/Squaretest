package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.foos.BeanWithJAXBListGetter;
import com.myapp.foos.SimpleBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
