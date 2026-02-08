package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testSerialize() {
        // Setup
        final OuterBean outerBean = new OuterBean();
        outerBean.setOuterBeanValue("outerBeanValue");
        final ResponseWrapper<InnerBean> wrappedInnerBean = new ResponseWrapper<>();
        wrappedInnerBean.setRequestHeaders(new HashMap<>());
        final InnerBean innerBean = new InnerBean();
        innerBean.setId(0L);
        wrappedInnerBean.setPayload(innerBean);
        outerBean.setWrappedInnerBean(wrappedInnerBean);

        // Run the test
        final String result = myClassUnderTest.serialize(outerBean);

        // Verify the results
        assertEquals("result", result);
    }
}
