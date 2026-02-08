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
        final ResponseWrapper<InnerBean> innerBean = new ResponseWrapper<>();
        innerBean.setRequestHeaders(new HashMap<>());
        final InnerBean innerBean1 = new InnerBean();
        innerBean1.setId(0L);
        innerBean1.setName("name");
        innerBean1.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        innerBean.setPayload(innerBean1);
        innerBean.setStatusCode(0);

        // Run the test
        final String result = myClassUnderTest.serialize(innerBean);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSerialize1() {
        // Setup
        final ResponseWrapper<OtherInnerBean> innerBean = new ResponseWrapper<>();
        innerBean.setRequestHeaders(new HashMap<>());
        final OtherInnerBean otherInnerBean = new OtherInnerBean();
        otherInnerBean.setFirstValue("firstValue");
        otherInnerBean.setSecondValue("secondValue");
        otherInnerBean.setThirdValue(0L);
        innerBean.setPayload(otherInnerBean);
        innerBean.setStatusCode(0);

        // Run the test
        final String result = myClassUnderTest.serialize1(innerBean);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSerialize2() {
        // Setup
        final ResponseWrapper<ResponseWrapper<OtherInnerBean>> innerBean = new ResponseWrapper<>();
        innerBean.setRequestHeaders(new HashMap<>());
        innerBean.setPayload(new ResponseWrapper<>());
        innerBean.setStatusCode(0);

        // Run the test
        final String result = myClassUnderTest.serialize2(innerBean);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSerialize3() {
        // Setup
        final ResponseWrapper<OuterBean> innerBean = new ResponseWrapper<>();
        innerBean.setRequestHeaders(new HashMap<>());
        final OuterBean outerBean = new OuterBean();
        outerBean.setOuterBeanValue("outerBeanValue");
        outerBean.setWrappedInnerBean(new ResponseWrapper<>());
        outerBean.setWrappedOtherInnerBean(new ResponseWrapper<>());
        innerBean.setPayload(outerBean);
        innerBean.setStatusCode(0);

        // Run the test
        final String result = myClassUnderTest.serialize3(innerBean);

        // Verify the results
        assertEquals("result", result);
    }
}
