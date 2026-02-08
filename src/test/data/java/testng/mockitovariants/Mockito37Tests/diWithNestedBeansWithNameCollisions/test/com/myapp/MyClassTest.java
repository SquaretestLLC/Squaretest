package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testSendRequest() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        final ResponseBean responseBean = new ResponseBean();
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        responseBean.setPrimaryBean(primaryBean);
        when(mockFooCreator.submitRequest()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendRequest("params");

        // Verify the results
    }

    @Test
    public void testSendDifferentRequest() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean = new ResponseBean();
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        responseBean.setPrimaryBean(primaryBean);
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendDifferentRequest("params");

        // Verify the results
    }

    @Test
    public void testSendBothRequests() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean = new ResponseBean();
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        responseBean.setPrimaryBean(primaryBean);
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
