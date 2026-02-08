package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testSerialize() {
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
