package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.multi.CloseableIterable;
import com.myapp.multi.CloseableIterableWithStaticCreatorMethod;
import com.myapp.multi.FooCreator;
import com.myapp.multi.SimpleBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testMakeTheStrings() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        final CloseableIterable<String> spyCloseableIterable = spy(CloseableIterable.empty());
        given(mockFooCreator.makeTheStrings("key")).willReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<String> result = myClassUnderTest.makeTheStrings("key");

        // Verify the results
        then(spyCloseableIterable).should().close();
    }

    @Test
    public void testMakeTheStrings_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        final CloseableIterable<String> spyCloseableIterable = spy(CloseableIterable.empty());
        given(mockFooCreator.makeTheStrings("key")).willReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<String> result = myClassUnderTest.makeTheStrings("key");

        // Verify the results
        then(spyCloseableIterable).should().close();
    }

    @Test
    public void testMakeTheStrings1() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        final CloseableIterableWithStaticCreatorMethod<String> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.of("value"));
        given(mockFooCreator.makeTheStrings1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<String> result = myClassUnderTest.makeTheStrings1("key");

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close();
    }

    @Test
    public void testMakeTheStrings1_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        final CloseableIterableWithStaticCreatorMethod<String> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.empty());
        given(mockFooCreator.makeTheStrings1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<String> result = myClassUnderTest.makeTheStrings1("key");

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close();
    }

    @Test
    public void testMakeTheBeans() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        final CloseableIterable<SimpleBean> spyCloseableIterable = spy(CloseableIterable.empty());
        given(mockFooCreator.makeTheBeans("key")).willReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<SimpleBean> result = myClassUnderTest.makeTheBeans("key");

        // Verify the results
        then(spyCloseableIterable).should().close();
    }

    @Test
    public void testMakeTheBeans_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        final CloseableIterable<SimpleBean> spyCloseableIterable = spy(CloseableIterable.empty());
        given(mockFooCreator.makeTheBeans("key")).willReturn(spyCloseableIterable);

        // Run the test
        final CloseableIterable<SimpleBean> result = myClassUnderTest.makeTheBeans("key");

        // Verify the results
        then(spyCloseableIterable).should().close();
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
        given(mockFooCreator.makeTheBeans1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> result = myClassUnderTest.makeTheBeans1("key");

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close();
    }

    @Test
    public void testMakeTheBeans1_FooCreatorReturnsNoItems() throws Exception {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> spyCloseableIterableWithStaticCreatorMethod = spy(
                CloseableIterableWithStaticCreatorMethod.empty());
        given(mockFooCreator.makeTheBeans1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod);

        // Run the test
        final CloseableIterableWithStaticCreatorMethod<SimpleBean> result = myClassUnderTest.makeTheBeans1("key");

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close();
    }
}
