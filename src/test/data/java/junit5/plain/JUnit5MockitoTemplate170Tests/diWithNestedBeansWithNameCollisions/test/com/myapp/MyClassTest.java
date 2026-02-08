package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        primaryBean.setTheString("theString");
        primaryBean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        primaryBean.setOtherSubBeans(Arrays.asList(otherSubBean));
        primaryBean.setValue(false);
        primaryBean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        primaryBean.setSubBean(subBean);
        responseBean.setPrimaryBean(primaryBean);
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
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        primaryBean.setTheString("theString");
        primaryBean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        primaryBean.setOtherSubBeans(Arrays.asList(otherSubBean));
        primaryBean.setValue(false);
        primaryBean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        primaryBean.setSubBean(subBean);
        responseBean.setPrimaryBean(primaryBean);
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
        final Bean primaryBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        primaryBean.setFooData(fooData);
        primaryBean.setTheString("theString");
        primaryBean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        primaryBean.setOtherSubBeans(Arrays.asList(otherSubBean));
        primaryBean.setValue(false);
        primaryBean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        primaryBean.setSubBean(subBean);
        responseBean.setPrimaryBean(primaryBean);
        when(mockFooCreator.submitRequest()).thenReturn(responseBean);

        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean1 = new ResponseBean();
        final Bean primaryBean1 = new Bean();
        final FooData fooData1 = new FooData();
        fooData1.setPurchaseId("purchaseId");
        fooData1.setNameOnTheLicense("nameOnTheLicense");
        fooData1.setOtherData(new OtherData("dataName"));
        primaryBean1.setFooData(fooData1);
        primaryBean1.setTheString("theString");
        primaryBean1.setTheInt(0);
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        primaryBean1.setOtherSubBeans(Arrays.asList(otherSubBean1));
        primaryBean1.setValue(false);
        primaryBean1.setOtherValue(false);
        final SubBean subBean1 = new SubBean();
        subBean1.setId(0L);
        subBean1.setName("name");
        primaryBean1.setSubBean(subBean1);
        responseBean1.setPrimaryBean(primaryBean1);
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean1);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
