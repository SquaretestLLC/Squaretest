package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateStringStream() {
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
    public void testCreateStringStream_FooReturnsNoItem() {
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
    public void testCreateBeanStream() {
        // Setup
        // Configure Foo.createBeanStream(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final Stream<Bean> spyStream = spy(Stream.of(bean));
        when(mockFoo.createBeanStream()).thenReturn(spyStream);

        // Run the test
        final Stream<Bean> result = myClassUnderTest.createBeanStream();

        // Verify the results
        verify(spyStream).close();
    }

    @Test
    public void testCreateBeanStream_FooReturnsNoItem() {
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
