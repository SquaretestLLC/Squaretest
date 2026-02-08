package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
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
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendDifferentRequest("params");

        // Verify the results
    }

    @Test
    void testSendBothRequests() {
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
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
