package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    void testSendRequest() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setResponseId("responseId");
        final Bean123 bean123 = new Bean123();
        bean123.setId("id");
        bean123.setName("name");
        responseBean.setPrimaryBeans(Arrays.asList(bean123));
        final Bean123 bean1231 = new Bean123();
        bean1231.setId("id");
        bean1231.setName("name");
        responseBean.setSecondaryBeans(Arrays.asList(bean1231));
        final Bean123 bean1232 = new Bean123();
        bean1232.setId("id");
        bean1232.setName("name");
        responseBean.setThirdBeans(Arrays.asList(bean1232));
        when(mockFooCreator.submitRequest()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendRequest("params");

        // Verify the results
    }

    @Test
    void testSendDifferentRequest() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setResponseId("responseId");
        final Bean123 bean123 = new Bean123();
        bean123.setId("id");
        bean123.setName("name");
        responseBean.setPrimaryBeans(Arrays.asList(bean123));
        final Bean123 bean1231 = new Bean123();
        bean1231.setId("id");
        bean1231.setName("name");
        responseBean.setSecondaryBeans(Arrays.asList(bean1231));
        final Bean123 bean1232 = new Bean123();
        bean1232.setId("id");
        bean1232.setName("name");
        responseBean.setThirdBeans(Arrays.asList(bean1232));
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendDifferentRequest("params");

        // Verify the results
    }

    @Test
    void testSendBothRequests() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setResponseId("responseId");
        final Bean123 bean123 = new Bean123();
        bean123.setId("id");
        bean123.setName("name");
        responseBean.setPrimaryBeans(Arrays.asList(bean123));
        final Bean123 bean1231 = new Bean123();
        bean1231.setId("id");
        bean1231.setName("name");
        responseBean.setSecondaryBeans(Arrays.asList(bean1231));
        final Bean123 bean1232 = new Bean123();
        bean1232.setId("id");
        bean1232.setName("name");
        responseBean.setThirdBeans(Arrays.asList(bean1232));
        when(mockFooCreator.submitRequest()).thenReturn(responseBean);

        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean1 = new ResponseBean();
        responseBean1.setResponseId("responseId");
        final Bean123 bean1233 = new Bean123();
        bean1233.setId("id");
        bean1233.setName("name");
        responseBean1.setPrimaryBeans(Arrays.asList(bean1233));
        final Bean123 bean1234 = new Bean123();
        bean1234.setId("id");
        bean1234.setName("name");
        responseBean1.setSecondaryBeans(Arrays.asList(bean1234));
        final Bean123 bean1235 = new Bean123();
        bean1235.setId("id");
        bean1235.setName("name");
        responseBean1.setThirdBeans(Arrays.asList(bean1235));
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean1);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
