package com.myapp;

import com.myapp.multi.CloseableIterable;
import com.myapp.multi.CloseableIterableWithStaticCreatorMethod;
import com.myapp.multi.FooCreator;
import com.myapp.multi.SimpleBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testMakeTheStrings() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        final CloseableIterable<String> spyCloseableIterable = spy(CloseableIterable.empty());
        when(mockFooCreator.makeTheStrings("key")).thenReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<String> result = myClassUnderTest.makeTheStrings("key");

        // Verify the results
        verify(spyCloseableIterable).close();
    }

    @Test
    public void testMakeTheStrings_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        final CloseableIterable<String> spyCloseableIterable = spy(CloseableIterable.empty());
        when(mockFooCreator.makeTheStrings("key")).thenReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<String> result = myClassUnderTest.makeTheStrings("key");

        // Verify the results
        verify(spyCloseableIterable).close();
    }

    @Test
    public void testMakeTheStrings1() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        final CloseableIterableWithStaticCreatorMethod<String> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.of("value"));
        when(mockFooCreator.makeTheStrings1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<String> result = myClassUnderTest.makeTheStrings1("key");

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close();
    }

    @Test
    public void testMakeTheStrings1_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        final CloseableIterableWithStaticCreatorMethod<String> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.empty());
        when(mockFooCreator.makeTheStrings1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<String> result = myClassUnderTest.makeTheStrings1("key");

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close();
    }

    @Test
    public void testMakeTheBeans() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        final CloseableIterable<SimpleBean> spyCloseableIterable = spy(CloseableIterable.empty());
        when(mockFooCreator.makeTheBeans("key")).thenReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<SimpleBean> result = myClassUnderTest.makeTheBeans("key");

        // Verify the results
        verify(spyCloseableIterable).close();
    }

    @Test
    public void testMakeTheBeans_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        final CloseableIterable<SimpleBean> spyCloseableIterable = spy(CloseableIterable.empty());
        when(mockFooCreator.makeTheBeans("key")).thenReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<SimpleBean> result = myClassUnderTest.makeTheBeans("key");

        // Verify the results
        verify(spyCloseableIterable).close();
    }

    @Test
    public void testMakeTheBeans1() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBean.setName("name");
        simpleBean.setDescription("description");
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.of(simpleBean));
        when(mockFooCreator.makeTheBeans1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> result = myClassUnderTest.makeTheBeans1("key");

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close();
    }

    @Test
    public void testMakeTheBeans1_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.empty());
        when(mockFooCreator.makeTheBeans1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> result = myClassUnderTest.makeTheBeans1("key");

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close();
    }
}
