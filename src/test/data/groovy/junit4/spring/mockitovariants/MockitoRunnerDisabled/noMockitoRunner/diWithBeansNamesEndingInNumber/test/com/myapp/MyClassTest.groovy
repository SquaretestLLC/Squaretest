package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testSendRequest() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        def responseBean = new ResponseBean()
        responseBean.setResponseId("responseId")
        def bean123 = new Bean123()
        bean123.setId("id")
        bean123.setName("name")
        responseBean.setPrimaryBeans([bean123])
        def bean1231 = new Bean123()
        bean1231.setId("id")
        bean1231.setName("name")
        responseBean.setSecondaryBeans([bean1231])
        when(mockFooCreator.submitRequest()).thenReturn(responseBean)

        // Run the test
        def result = myClassUnderTest.sendRequest("params")

        // Verify the results
    }

    @Test
    void testSendDifferentRequest() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        def responseBean = new ResponseBean()
        responseBean.setResponseId("responseId")
        def bean123 = new Bean123()
        bean123.setId("id")
        bean123.setName("name")
        responseBean.setPrimaryBeans([bean123])
        def bean1231 = new Bean123()
        bean1231.setId("id")
        bean1231.setName("name")
        responseBean.setSecondaryBeans([bean1231])
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean)

        // Run the test
        def result = myClassUnderTest.sendDifferentRequest("params")

        // Verify the results
    }

    @Test
    void testSendBothRequests() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        def responseBean = new ResponseBean()
        responseBean.setResponseId("responseId")
        def bean123 = new Bean123()
        bean123.setId("id")
        bean123.setName("name")
        responseBean.setPrimaryBeans([bean123])
        def bean1231 = new Bean123()
        bean1231.setId("id")
        bean1231.setName("name")
        responseBean.setSecondaryBeans([bean1231])
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean)

        // Run the test
        def result = myClassUnderTest.sendBothRequests("params")

        // Verify the results
        verify(mockFooCreator).submitRequest()
    }
}
