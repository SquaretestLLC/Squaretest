package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @After
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
