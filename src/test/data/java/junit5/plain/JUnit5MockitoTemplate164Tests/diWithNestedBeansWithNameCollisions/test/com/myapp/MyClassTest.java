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
        primaryBean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean1.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
    void testSendDifferentRequest() {
        // Setup
        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean = new ResponseBean();
        final Bean primaryBean = new Bean();
        primaryBean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean1.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
    void testSendBothRequests() {
        // Setup
        // Configure FooCreator.submitRequest(...).
        final ResponseBean responseBean = new ResponseBean();
        final Bean primaryBean = new Bean();
        primaryBean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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
        bean1.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
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

        // Configure FooCreator.submitRequest2(...).
        final ResponseBean responseBean1 = new ResponseBean();
        final Bean primaryBean1 = new Bean();
        primaryBean1.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        primaryBean1.setTheString("theString");
        primaryBean1.setTheInt(0);
        final OtherSubBean otherSubBean4 = new OtherSubBean();
        otherSubBean4.setOtherBeanID("otherBeanID");
        otherSubBean4.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        primaryBean1.setOtherSubBeans(Arrays.asList(otherSubBean4));
        primaryBean1.setValue(false);
        primaryBean1.setOtherValue(false);
        final SubBean subBean4 = new SubBean();
        subBean4.setId(0L);
        subBean4.setName("name");
        primaryBean1.setSubBean(subBean4);
        responseBean1.setPrimaryBean(primaryBean1);
        final SubBean subBean5 = new SubBean();
        subBean5.setId(0L);
        subBean5.setName("name");
        responseBean1.setSubBean(subBean5);
        final OtherSubBean otherSubBean5 = new OtherSubBean();
        otherSubBean5.setOtherBeanID("otherBeanID");
        otherSubBean5.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        responseBean1.setOtherSubBean(otherSubBean5);
        final Bean bean2 = new Bean();
        bean2.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        bean2.setTheString("theString");
        bean2.setTheInt(0);
        final OtherSubBean otherSubBean6 = new OtherSubBean();
        otherSubBean6.setOtherBeanID("otherBeanID");
        otherSubBean6.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean2.setOtherSubBeans(Arrays.asList(otherSubBean6));
        bean2.setValue(false);
        bean2.setOtherValue(false);
        final SubBean subBean6 = new SubBean();
        subBean6.setId(0L);
        subBean6.setName("name");
        bean2.setSubBean(subBean6);
        responseBean1.setSecondaryBeans(Arrays.asList(bean2));
        final Bean bean3 = new Bean();
        bean3.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        bean3.setTheString("theString");
        bean3.setTheInt(0);
        final OtherSubBean otherSubBean7 = new OtherSubBean();
        otherSubBean7.setOtherBeanID("otherBeanID");
        otherSubBean7.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean3.setOtherSubBeans(Arrays.asList(otherSubBean7));
        bean3.setValue(false);
        bean3.setOtherValue(false);
        final SubBean subBean7 = new SubBean();
        subBean7.setId(0L);
        subBean7.setName("name");
        bean3.setSubBean(subBean7);
        responseBean1.setThirdBeans(Arrays.asList(bean3));
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean1);

        // Run the test
        final ResponseBean result = myClassUnderTest.sendBothRequests("params");

        // Verify the results
        verify(mockFooCreator).submitRequest();
    }
}
