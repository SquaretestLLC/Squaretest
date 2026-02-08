package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testCreateStringStream() {
        // Setup
        // Configure Foo.createStringStream(...).
        final Stream<String> spyStream = spy(Stream.of("value"));
        given(mockFoo.createStringStream()).willReturn(spyStream);

        // Run the test
        final Stream<String> result = myClassUnderTest.createStringStream();

        // Verify the results
        then(spyStream).should().close();
    }

    @Test
    public void testCreateStringStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createStringStream(...).
        final Stream<String> spyStream = spy(Stream.empty());
        given(mockFoo.createStringStream()).willReturn(spyStream);

        // Run the test
        final Stream<String> result = myClassUnderTest.createStringStream();

        // Verify the results
        then(spyStream).should().close();
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
        given(mockFoo.createBeanStream()).willReturn(spyStream);

        // Run the test
        final Stream<Bean> result = myClassUnderTest.createBeanStream();

        // Verify the results
        then(spyStream).should().close();
    }

    @Test
    public void testCreateBeanStream_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createBeanStream(...).
        final Stream<Bean> spyStream = spy(Stream.empty());
        given(mockFoo.createBeanStream()).willReturn(spyStream);

        // Run the test
        final Stream<Bean> result = myClassUnderTest.createBeanStream();

        // Verify the results
        then(spyStream).should().close();
    }
}
