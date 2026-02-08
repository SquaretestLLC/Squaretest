package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testSendRequest() {
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
        given(mockFooCreator.submitRequest()).willReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendRequest("params");

        // Verify the results
    }

    @Test
    public void testSendDifferentRequest() {
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
        given(mockFooCreator.submitRequest2()).willReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendDifferentRequest("params");

        // Verify the results
    }

    @Test
    public void testSendBothRequests() {
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
        given(mockFooCreator.submitRequest2()).willReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        then(mockFooCreator).should().submitRequest();
    }
}
