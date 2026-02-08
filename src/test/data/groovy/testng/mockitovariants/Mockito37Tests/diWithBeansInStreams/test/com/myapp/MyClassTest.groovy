package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.stream.Stream

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
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
