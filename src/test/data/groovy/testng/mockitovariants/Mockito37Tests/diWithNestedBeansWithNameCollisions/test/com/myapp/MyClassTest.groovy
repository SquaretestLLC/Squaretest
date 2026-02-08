package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testSendRequest() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        def responseBean = new ResponseBean()
        def primaryBean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        primaryBean.setFooData(fooData)
        responseBean.setPrimaryBean(primaryBean)
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
        def primaryBean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        primaryBean.setFooData(fooData)
        responseBean.setPrimaryBean(primaryBean)
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
        def primaryBean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        primaryBean.setFooData(fooData)
        responseBean.setPrimaryBean(primaryBean)
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean)

        // Run the test
        def result = myClassUnderTest.sendBothRequests("params")

        // Verify the results
        verify(mockFooCreator).submitRequest()
    }
}
