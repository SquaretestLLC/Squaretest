package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator);
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
