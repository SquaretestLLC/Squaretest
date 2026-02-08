package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.stream.Stream

import static org.mockito.Mockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testCreateStringStream() {
        // Setup
        // Configure Foo.createStringStream(...).
        def spyStream = spy(Stream.of("value"))
        when(mockFoo.createStringStream()).thenReturn(spyStream)

        // Run the test
        def result = myClassUnderTest.createStringStream()

        // Verify the results
        verify(spyStream).close()
    }

    @Test
    void testCreateStringStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createStringStream(...).
        def spyStream = spy(Stream.empty())
        when(mockFoo.createStringStream()).thenReturn(spyStream)

        // Run the test
        def result = myClassUnderTest.createStringStream()

        // Verify the results
        verify(spyStream).close()
    }

    @Test
    void testCreateBeanStream() {
        // Setup
        // Configure Foo.createBeanStream(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        bean.setTheInt(0)
        bean.setOtherBeanMatrix([] as OtherSubBean[][])
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherBeanArray([otherSubBean] as OtherSubBean[])
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean1])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        bean.setSubBean(subBean)
        def spyStream = spy(Stream.of(bean))
        when(mockFoo.createBeanStream()).thenReturn(spyStream)

        // Run the test
        def result = myClassUnderTest.createBeanStream()

        // Verify the results
        verify(spyStream).close()
    }

    @Test
    void testCreateBeanStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createBeanStream(...).
        def spyStream = spy(Stream.empty())
        when(mockFoo.createBeanStream()).thenReturn(spyStream)

        // Run the test
        def result = myClassUnderTest.createBeanStream()

        // Verify the results
        verify(spyStream).close()
    }
}
