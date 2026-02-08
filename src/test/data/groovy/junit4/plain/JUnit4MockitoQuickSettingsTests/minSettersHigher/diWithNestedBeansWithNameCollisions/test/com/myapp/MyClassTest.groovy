package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator)
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
        primaryBean.setTheString("theString")
        primaryBean.setTheInt(0)
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        primaryBean.setOtherSubBeans([otherSubBean])
        primaryBean.setValue(false)
        primaryBean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        primaryBean.setSubBean(subBean)
        responseBean.setPrimaryBean(primaryBean)
        def subBean1 = new SubBean()
        subBean1.setId(0L)
        subBean1.setName("name")
        responseBean.setSubBean(subBean1)
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        responseBean.setOtherSubBean(otherSubBean1)
        def bean = new Bean()
        def fooData1 = new FooData()
        fooData1.setPurchaseId("purchaseId")
        fooData1.setNameOnTheLicense("nameOnTheLicense")
        fooData1.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData1)
        bean.setTheString("theString")
        bean.setTheInt(0)
        def otherSubBean2 = new OtherSubBean()
        otherSubBean2.setOtherBeanID("otherBeanID")
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean2])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean2 = new SubBean()
        subBean2.setId(0L)
        subBean2.setName("name")
        bean.setSubBean(subBean2)
        responseBean.setSecondaryBeans([bean])
        def bean1 = new Bean()
        def fooData2 = new FooData()
        fooData2.setPurchaseId("purchaseId")
        fooData2.setNameOnTheLicense("nameOnTheLicense")
        fooData2.setOtherData(new OtherData("dataName"))
        bean1.setFooData(fooData2)
        bean1.setTheString("theString")
        bean1.setTheInt(0)
        def otherSubBean3 = new OtherSubBean()
        otherSubBean3.setOtherBeanID("otherBeanID")
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean1.setOtherSubBeans([otherSubBean3])
        bean1.setValue(false)
        bean1.setOtherValue(false)
        def subBean3 = new SubBean()
        subBean3.setId(0L)
        subBean3.setName("name")
        bean1.setSubBean(subBean3)
        responseBean.setThirdBeans([bean1])
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
        primaryBean.setTheString("theString")
        primaryBean.setTheInt(0)
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        primaryBean.setOtherSubBeans([otherSubBean])
        primaryBean.setValue(false)
        primaryBean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        primaryBean.setSubBean(subBean)
        responseBean.setPrimaryBean(primaryBean)
        def subBean1 = new SubBean()
        subBean1.setId(0L)
        subBean1.setName("name")
        responseBean.setSubBean(subBean1)
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        responseBean.setOtherSubBean(otherSubBean1)
        def bean = new Bean()
        def fooData1 = new FooData()
        fooData1.setPurchaseId("purchaseId")
        fooData1.setNameOnTheLicense("nameOnTheLicense")
        fooData1.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData1)
        bean.setTheString("theString")
        bean.setTheInt(0)
        def otherSubBean2 = new OtherSubBean()
        otherSubBean2.setOtherBeanID("otherBeanID")
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean2])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean2 = new SubBean()
        subBean2.setId(0L)
        subBean2.setName("name")
        bean.setSubBean(subBean2)
        responseBean.setSecondaryBeans([bean])
        def bean1 = new Bean()
        def fooData2 = new FooData()
        fooData2.setPurchaseId("purchaseId")
        fooData2.setNameOnTheLicense("nameOnTheLicense")
        fooData2.setOtherData(new OtherData("dataName"))
        bean1.setFooData(fooData2)
        bean1.setTheString("theString")
        bean1.setTheInt(0)
        def otherSubBean3 = new OtherSubBean()
        otherSubBean3.setOtherBeanID("otherBeanID")
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean1.setOtherSubBeans([otherSubBean3])
        bean1.setValue(false)
        bean1.setOtherValue(false)
        def subBean3 = new SubBean()
        subBean3.setId(0L)
        subBean3.setName("name")
        bean1.setSubBean(subBean3)
        responseBean.setThirdBeans([bean1])
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
        primaryBean.setTheString("theString")
        primaryBean.setTheInt(0)
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        primaryBean.setOtherSubBeans([otherSubBean])
        primaryBean.setValue(false)
        primaryBean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        primaryBean.setSubBean(subBean)
        responseBean.setPrimaryBean(primaryBean)
        def subBean1 = new SubBean()
        subBean1.setId(0L)
        subBean1.setName("name")
        responseBean.setSubBean(subBean1)
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        responseBean.setOtherSubBean(otherSubBean1)
        def bean = new Bean()
        def fooData1 = new FooData()
        fooData1.setPurchaseId("purchaseId")
        fooData1.setNameOnTheLicense("nameOnTheLicense")
        fooData1.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData1)
        bean.setTheString("theString")
        bean.setTheInt(0)
        def otherSubBean2 = new OtherSubBean()
        otherSubBean2.setOtherBeanID("otherBeanID")
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean2])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean2 = new SubBean()
        subBean2.setId(0L)
        subBean2.setName("name")
        bean.setSubBean(subBean2)
        responseBean.setSecondaryBeans([bean])
        def bean1 = new Bean()
        def fooData2 = new FooData()
        fooData2.setPurchaseId("purchaseId")
        fooData2.setNameOnTheLicense("nameOnTheLicense")
        fooData2.setOtherData(new OtherData("dataName"))
        bean1.setFooData(fooData2)
        bean1.setTheString("theString")
        bean1.setTheInt(0)
        def otherSubBean3 = new OtherSubBean()
        otherSubBean3.setOtherBeanID("otherBeanID")
        otherSubBean3.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean1.setOtherSubBeans([otherSubBean3])
        bean1.setValue(false)
        bean1.setOtherValue(false)
        def subBean3 = new SubBean()
        subBean3.setId(0L)
        subBean3.setName("name")
        bean1.setSubBean(subBean3)
        responseBean.setThirdBeans([bean1])
        when(mockFooCreator.submitRequest2()).thenReturn(responseBean)

        // Run the test
        def result = myClassUnderTest.sendBothRequests("params")

        // Verify the results
        verify(mockFooCreator).submitRequest()
    }
}
