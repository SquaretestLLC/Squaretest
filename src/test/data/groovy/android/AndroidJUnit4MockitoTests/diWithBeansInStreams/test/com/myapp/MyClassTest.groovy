package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import java.util.stream.Stream

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
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
