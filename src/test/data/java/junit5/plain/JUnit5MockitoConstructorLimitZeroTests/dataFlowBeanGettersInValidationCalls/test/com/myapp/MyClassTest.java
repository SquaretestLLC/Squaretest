package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetData() {
        // Setup
        // Configure FooService.makeFirstServiceCall(...).
        final FirstResponseBean firstResponseBean = new FirstResponseBean();
        firstResponseBean.setFirstResponseBeanId("firstResponseBeanId");
        firstResponseBean.setName("name");
        when(mockFooService.makeFirstServiceCall("getDataParam")).thenReturn(firstResponseBean);

        // Configure FooService.makeSecondServiceCall(...).
        final SecondResponseBean secondResponseBean = new SecondResponseBean();
        secondResponseBean.setSecondResponseBeanId("secondResponseBeanId");
        secondResponseBean.setName("name");
        when(mockFooService.makeSecondServiceCall("firstResponseBeanId")).thenReturn(secondResponseBean);

        // Run the test
        final String result = myClassUnderTest.getData("getDataParam");

        // Verify the results
        assertEquals("secondResponseBeanId", result);
    }

    @Test
    void testGetData1() {
        // Setup
        // Configure FooService.makeFirstServiceCall1(...).
        final FirstResponseBean1 firstResponseBean1 = new FirstResponseBean1();
        firstResponseBean1.setFirstResponseBeanId1("firstResponseBeanId1");
        firstResponseBean1.setName("name");
        when(mockFooService.makeFirstServiceCall1("getDataParam1")).thenReturn(firstResponseBean1);

        // Configure FooService.makeSecondServiceCall1(...).
        final SecondResponseBean1 secondResponseBean1 = new SecondResponseBean1();
        secondResponseBean1.setSecondResponseBeanId1("secondResponseBeanId1");
        secondResponseBean1.setName("name");
        when(mockFooService.makeSecondServiceCall1("firstResponseBeanId1")).thenReturn(secondResponseBean1);

        // Run the test
        final String result = myClassUnderTest.getData1("getDataParam1");

        // Verify the results
        assertEquals("secondResponseBeanId1", result);
    }

    @Test
    void testGetData2() {
        // Setup
        when(mockFooService.makeStringServiceCall("getDataParam2")).thenReturn("secondServiceCallId1");

        // Configure FooService.makeSecondServiceCall2(...).
        final SecondResponseBean2 secondResponseBean2 = new SecondResponseBean2();
        secondResponseBean2.setSecondResponseBeanId2("secondResponseBeanId2");
        secondResponseBean2.setName("name");
        when(mockFooService.makeSecondServiceCall2("secondServiceCallId1")).thenReturn(secondResponseBean2);

        // Run the test
        final String result = myClassUnderTest.getData2("getDataParam2");

        // Verify the results
        assertEquals("secondResponseBeanId2", result);
    }

    @Test
    void testGetData3() {
        // Setup
        final FirstResponseBean2 getData3Param = new FirstResponseBean2();
        getData3Param.setFirstResponseBeanId2("firstResponseBeanId2");
        getData3Param.setName("name");

        when(mockFooService.makeStringServiceCall1("firstResponseBeanId2")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData3(getData3Param);

        // Verify the results
        assertEquals("result", result);
    }
}
