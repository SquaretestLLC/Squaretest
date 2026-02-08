package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testCreateStringStream() {
        // Setup
        // Configure Foo.createStringStream(...).
        final Stream<String> spyStream = spy(Stream.of("value"));
        when(mockFoo.createStringStream()).thenReturn(spyStream);

        // Run the test
        final Stream<String> result = myClassUnderTest.createStringStream();

        // Verify the results
        verify(spyStream).close();
    }

    @Test
    void testCreateStringStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createStringStream(...).
        final Stream<String> spyStream = spy(Stream.empty());
        when(mockFoo.createStringStream()).thenReturn(spyStream);

        // Run the test
        final Stream<String> result = myClassUnderTest.createStringStream();

        // Verify the results
        verify(spyStream).close();
    }

    @Test
    void testCreateBeanStream() {
        // Setup
        // Configure Foo.createBeanStream(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherBeanMatrix(new OtherSubBean[][]{{otherSubBean}});
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherBeanArray(new OtherSubBean[]{otherSubBean1});
        final OtherSubBean otherSubBean2 = new OtherSubBean();
        otherSubBean2.setOtherBeanID("otherBeanID");
        otherSubBean2.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean2));
        final Stream<Bean> spyStream = spy(Stream.of(bean));
        when(mockFoo.createBeanStream()).thenReturn(spyStream);

        // Run the test
        final Stream<Bean> result = myClassUnderTest.createBeanStream();

        // Verify the results
        verify(spyStream).close();
    }

    @Test
    void testCreateBeanStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createBeanStream(...).
        final Stream<Bean> spyStream = spy(Stream.empty());
        when(mockFoo.createBeanStream()).thenReturn(spyStream);

        // Run the test
        final Stream<Bean> result = myClassUnderTest.createBeanStream();

        // Verify the results
        verify(spyStream).close();
    }
}
