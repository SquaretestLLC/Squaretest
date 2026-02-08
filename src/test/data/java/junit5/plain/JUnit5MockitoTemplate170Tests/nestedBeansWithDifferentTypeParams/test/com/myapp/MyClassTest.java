package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
        innerBean.setName("name");
        innerBean.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        wrappedInnerBean.setPayload(innerBean);
        wrappedInnerBean.setStatusCode(0);
        outerBean.setWrappedInnerBean(wrappedInnerBean);
        final ResponseWrapper<OtherInnerBean> wrappedOtherInnerBean = new ResponseWrapper<>();
        wrappedOtherInnerBean.setRequestHeaders(new HashMap<>());
        final OtherInnerBean otherInnerBean = new OtherInnerBean();
        otherInnerBean.setFirstValue("firstValue");
        otherInnerBean.setSecondValue("secondValue");
        otherInnerBean.setThirdValue(0L);
        wrappedOtherInnerBean.setPayload(otherInnerBean);
        wrappedOtherInnerBean.setStatusCode(0);
        outerBean.setWrappedOtherInnerBean(wrappedOtherInnerBean);

        // Run the test
        final String result = myClassUnderTest.serialize(outerBean);

        // Verify the results
        assertEquals("result", result);
    }
}
