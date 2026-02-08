package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        final SubBean subBean1 = new SubBean();
        subBean1.setId(0L);
        subBean1.setName("name");
        responseBean.setSubBean(subBean1);
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        responseBean.setOtherSubBean(otherSubBean1);
        final Bean bean = new Bean();
        final FooData fooData1 = new FooData();
        fooData1.setPurchaseId("purchaseId");
        fooData1.setNameOnTheLicense("nameOnTheLicense");
        fooData1.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData1);
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean2 = new OtherSubBean();
        otherSubBean2.setOtherBeanID("otherBeanID");
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean2));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean2 = new SubBean();
        subBean2.setId(0L);
        subBean2.setName("name");
        bean.setSubBean(subBean2);
        responseBean.setSecondaryBeans(Arrays.asList(bean));
        final Bean bean1 = new Bean();
        final FooData fooData2 = new FooData();
        fooData2.setPurchaseId("purchaseId");
        fooData2.setNameOnTheLicense("nameOnTheLicense");
        fooData2.setOtherData(new OtherData("dataName"));
        bean1.setFooData(fooData2);
        bean1.setTheString("theString");
        bean1.setTheInt(0);
        final OtherSubBean otherSubBean3 = new OtherSubBean();
        otherSubBean3.setOtherBeanID("otherBeanID");
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean1.setOtherSubBeans(Arrays.asList(otherSubBean3));
        bean1.setValue(false);
        bean1.setOtherValue(false);
        final SubBean subBean3 = new SubBean();
        subBean3.setId(0L);
        subBean3.setName("name");
        bean1.setSubBean(subBean3);
        responseBean.setThirdBeans(Arrays.asList(bean1));
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
        final SubBean subBean1 = new SubBean();
        subBean1.setId(0L);
        subBean1.setName("name");
        responseBean.setSubBean(subBean1);
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        responseBean.setOtherSubBean(otherSubBean1);
        final Bean bean = new Bean();
        final FooData fooData1 = new FooData();
        fooData1.setPurchaseId("purchaseId");
        fooData1.setNameOnTheLicense("nameOnTheLicense");
        fooData1.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData1);
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean2 = new OtherSubBean();
        otherSubBean2.setOtherBeanID("otherBeanID");
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean2));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean2 = new SubBean();
        subBean2.setId(0L);
        subBean2.setName("name");
        bean.setSubBean(subBean2);
        responseBean.setSecondaryBeans(Arrays.asList(bean));
        final Bean bean1 = new Bean();
        final FooData fooData2 = new FooData();
        fooData2.setPurchaseId("purchaseId");
        fooData2.setNameOnTheLicense("nameOnTheLicense");
        fooData2.setOtherData(new OtherData("dataName"));
        bean1.setFooData(fooData2);
        bean1.setTheString("theString");
        bean1.setTheInt(0);
        final OtherSubBean otherSubBean3 = new OtherSubBean();
        otherSubBean3.setOtherBeanID("otherBeanID");
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean1.setOtherSubBeans(Arrays.asList(otherSubBean3));
        bean1.setValue(false);
        bean1.setOtherValue(false);
        final SubBean subBean3 = new SubBean();
        subBean3.setId(0L);
        subBean3.setName("name");
        bean1.setSubBean(subBean3);
        responseBean.setThirdBeans(Arrays.asList(bean1));
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
        final SubBean subBean1 = new SubBean();
        subBean1.setId(0L);
        subBean1.setName("name");
        responseBean.setSubBean(subBean1);
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        responseBean.setOtherSubBean(otherSubBean1);
        final Bean bean = new Bean();
        final FooData fooData1 = new FooData();
        fooData1.setPurchaseId("purchaseId");
        fooData1.setNameOnTheLicense("nameOnTheLicense");
        fooData1.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData1);
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean2 = new OtherSubBean();
        otherSubBean2.setOtherBeanID("otherBeanID");
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean2));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean2 = new SubBean();
        subBean2.setId(0L);
        subBean2.setName("name");
        bean.setSubBean(subBean2);
        responseBean.setSecondaryBeans(Arrays.asList(bean));
        final Bean bean1 = new Bean();
        final FooData fooData2 = new FooData();
        fooData2.setPurchaseId("purchaseId");
        fooData2.setNameOnTheLicense("nameOnTheLicense");
        fooData2.setOtherData(new OtherData("dataName"));
        bean1.setFooData(fooData2);
        bean1.setTheString("theString");
        bean1.setTheInt(0);
        final OtherSubBean otherSubBean3 = new OtherSubBean();
        otherSubBean3.setOtherBeanID("otherBeanID");
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean1.setOtherSubBeans(Arrays.asList(otherSubBean3));
        bean1.setValue(false);
        bean1.setOtherValue(false);
        final SubBean subBean3 = new SubBean();
        subBean3.setId(0L);
        subBean3.setName("name");
        bean1.setSubBean(subBean3);
        responseBean.setThirdBeans(Arrays.asList(bean1));
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
