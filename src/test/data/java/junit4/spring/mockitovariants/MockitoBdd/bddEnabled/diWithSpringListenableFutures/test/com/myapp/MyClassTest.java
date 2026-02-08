package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

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
    public void testMax() {
        assertEquals("result", MyClass.max(Arrays.asList("value")));
    }

    @Test
    public void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<SimpleBean> simpleBeanListenableFuture = AsyncResult.forValue(simpleBean);
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final ListenableFuture<SimpleBean> simpleBeanListenableFuture = AsyncResult.forExecutionException(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfBean("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        willReturn(new SettableListenableFuture<>()).given(mockFoo).createSettableFutureOfBeanSubtype("key");

        // Run the test
        final SettableListenableFuture<? extends SimpleBean> result = myClassUnderTest.createSettableFutureOfBeanSubtype(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfListOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<List<SimpleBean>> listListenableFuture = AsyncResult.forValue(Arrays.asList(simpleBean));
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final ListenableFuture<List<SimpleBean>> listListenableFuture = AsyncResult.forExecutionException(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfBean("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateNestedListenableFutureOfBean() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = AsyncResult.forValue(
                AsyncResult.forValue(simpleBean));
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture);

        // Run the test
        final ListenableFuture<ListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        final ListenableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = AsyncResult.forExecutionException(
                new Exception("message"));
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture);

        // Run the test
        final ListenableFuture<ListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateNestedSettableFutureOfBean() {
        // Setup
        given(mockFoo.createNestedSettableFutureOfBean("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<SettableListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key")).willReturn(AsyncResult.forValue(new Bar()));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBar() {
        // Setup
        given(mockFoo.createSettableFutureOfBar("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown() {
        // Setup
        willReturn(AsyncResult.forValue("value")).given(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        willReturn(AsyncResult.forExecutionException(new Exception("message"))).given(
                mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfUnknown() {
        // Setup
        willReturn(new SettableListenableFuture<>()).given(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableListenableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfUnknown("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateListWithWildcard() {
        // Setup
        willReturn(Arrays.asList("value")).given(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        willReturn(Collections.emptyList()).given(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testCreateMapWithWildcard() {
        // Setup
        final Map<String, ?> expectedResult = new HashMap<>();
        willReturn(new HashMap<>()).given(mockFoo).createMapWithWildcard("key");

        // Run the test
        final Map<String, ?> result = myClassUnderTest.createMapWithWildcard("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateListenableFutureOfInt() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key")).willReturn(AsyncResult.forValue(0));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfInt() {
        // Setup
        given(mockFoo.createSettableFutureOfInt("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key")).willReturn(AsyncResult.forValue(null));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfNoType() {
        // Setup
        given(mockFoo.createSettableFutureOfNoType("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = AsyncResult.forValue(new String[]{"value"});
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = AsyncResult.forExecutionException(new Exception("message"));
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfStringArray() {
        // Setup
        given(mockFoo.createSettableFutureOfStringArray("key")).willReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }
}
