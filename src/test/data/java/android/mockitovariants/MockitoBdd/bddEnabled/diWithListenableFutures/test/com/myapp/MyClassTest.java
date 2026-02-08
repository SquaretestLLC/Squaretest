package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
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
        final ListenableFuture<SimpleBean> simpleBeanListenableFuture = Futures.immediateFuture(simpleBean);
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final ListenableFuture<SimpleBean> simpleBeanListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfBeanSubtype("key");

        // Run the test
        final SettableFuture<? extends SimpleBean> result = myClassUnderTest.createSettableFutureOfBeanSubtype("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfListOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<List<SimpleBean>> listListenableFuture = Futures.immediateFuture(
                Arrays.asList(simpleBean));
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final ListenableFuture<List<SimpleBean>> listListenableFuture = Futures.immediateFuture(
                Collections.emptyList());
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final ListenableFuture<List<SimpleBean>> listListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    public void testCreateNestedListenableFutureOfBean() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = Futures.immediateFuture(
                Futures.immediateFuture(simpleBean));
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
        final ListenableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = Futures.immediateFailedFuture(
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
        given(mockFoo.createNestedSettableFutureOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SettableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key")).willReturn(Futures.immediateFuture(new Bar()));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfBar() {
        // Setup
        given(mockFoo.createSettableFutureOfBar("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown() {
        // Setup
        willReturn(Futures.immediateFuture("value")).given(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        willReturn(Futures.immediateFailedFuture(new Exception("message"))).given(
                mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfUnknown() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfUnknown("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

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
        given(mockFoo.createListenableFutureOfInt("key")).willReturn(Futures.immediateFuture(0));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfInt() {
        // Setup
        given(mockFoo.createSettableFutureOfInt("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key")).willReturn(Futures.immediateFuture(null));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfNoType() {
        // Setup
        given(mockFoo.createSettableFutureOfNoType("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFuture(new String[]{"value"});
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(Futures.immediateFuture(new String[]{}));

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFailedFuture(new Exception("message"));
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    public void testCreateSettableFutureOfStringArray() {
        // Setup
        given(mockFoo.createSettableFutureOfStringArray("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }
}
