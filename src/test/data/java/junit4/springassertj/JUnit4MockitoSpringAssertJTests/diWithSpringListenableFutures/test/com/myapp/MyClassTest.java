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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        assertThat(MyClass.max(Arrays.asList("value"))).isEqualTo("result");
    }

    @Test
    public void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<SimpleBean> simpleBeanListenableFuture = AsyncResult.forValue(simpleBean);
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

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
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        doReturn(new SettableListenableFuture<>()).when(mockFoo).createSettableFutureOfBeanSubtype("key");

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
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

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
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(new SettableListenableFuture<>());

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
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture);

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
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture);

        // Run the test
        final ListenableFuture<ListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateNestedSettableFutureOfBean() {
        // Setup
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<SettableListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(AsyncResult.forValue(new Bar()));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key"))
                .thenReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBar() {
        // Setup
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown() {
        // Setup
        doReturn(AsyncResult.forValue("value")).when(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        doReturn(AsyncResult.forExecutionException(new Exception("message"))).when(
                mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfUnknown() {
        // Setup
        doReturn(new SettableListenableFuture<>()).when(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableListenableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateListWithWildcard() {
        // Setup
        doReturn(Arrays.asList("value")).when(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    public void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        doReturn(Collections.emptyList()).when(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testCreateMapWithWildcard() {
        // Setup
        final Map<String, ?> expectedResult = new HashMap<>();
        doReturn(new HashMap<>()).when(mockFoo).createMapWithWildcard("key");

        // Run the test
        final Map<String, ?> result = myClassUnderTest.createMapWithWildcard("key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateListenableFutureOfInt() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(AsyncResult.forValue(0));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key"))
                .thenReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfInt() {
        // Setup
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(AsyncResult.forValue(null));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key"))
                .thenReturn(AsyncResult.forExecutionException(new Exception("message")));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfNoType() {
        // Setup
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = AsyncResult.forValue(new String[]{"value"});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = AsyncResult.forExecutionException(new Exception("message"));
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfStringArray() {
        // Setup
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(new SettableListenableFuture<>());

        // Run the test
        final SettableListenableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }
}
