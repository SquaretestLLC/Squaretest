package com.myapp;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
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
    void testMax() {
        assertEquals("result", MyClass.max(Arrays.asList("value")));
    }

    @Test
    void testCreateListenableFutureOfBean() {
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
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
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
    void testCreateSettableFutureOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfBeanSubtype("key");

        // Run the test
        final SettableFuture<? extends SimpleBean> result = myClassUnderTest.createSettableFutureOfBeanSubtype("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean() {
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
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
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
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
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
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean() {
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
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
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
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        given(mockFoo.createNestedSettableFutureOfBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SettableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key")).willReturn(Futures.immediateFuture(new Bar()));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        given(mockFoo.createSettableFutureOfBar("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        willReturn(Futures.immediateFuture("value")).given(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        willReturn(Futures.immediateFailedFuture(new Exception("message"))).given(
                mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfUnknown("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListWithWildcard() {
        // Setup
        willReturn(Arrays.asList("value")).given(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        willReturn(Collections.emptyList()).given(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCreateMapWithWildcard() {
        // Setup
        final Map<String, ?> expectedResult = new HashMap<>();
        willReturn(new HashMap<>()).given(mockFoo).createMapWithWildcard("key");

        // Run the test
        final Map<String, ?> result = myClassUnderTest.createMapWithWildcard("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreateListenableFutureOfInt() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key")).willReturn(Futures.immediateFuture(0));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        given(mockFoo.createSettableFutureOfInt("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key")).willReturn(Futures.immediateFuture(null));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        given(mockFoo.createSettableFutureOfNoType("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFuture(new String[]{"value"});
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(Futures.immediateFuture(new String[]{}));

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFailedFuture(new Exception("message"));
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        given(mockFoo.createSettableFutureOfStringArray("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalString(...).
        final ListenableFuture<Optional<String>> optionalListenableFuture = Futures.immediateFuture(
                Optional.of("value"));
        given(mockFoo.createListenableFutureOfOptionalString("key")).willReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString_FooReturnsNoItem() {
        // Setup
        given(mockFoo.createListenableFutureOfOptionalString("key"))
                .willReturn(Futures.immediateFuture(Optional.empty()));

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalString(...).
        final ListenableFuture<Optional<String>> optionalListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfOptionalString("key")).willReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalString() {
        // Setup
        given(mockFoo.createSettableFutureOfOptionalString("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Optional<String>> result = myClassUnderTest.createSettableFutureOfOptionalString("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalSimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ListenableFuture<Optional<SimpleBean>> optionalListenableFuture = Futures.immediateFuture(
                Optional.of(simpleBean));
        given(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).willReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean_FooReturnsNoItem() {
        // Setup
        given(mockFoo.createListenableFutureOfOptionalSimpleBean("key"))
                .willReturn(Futures.immediateFuture(Optional.empty()));

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalSimpleBean(...).
        final ListenableFuture<Optional<SimpleBean>> optionalListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        given(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).willReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalSimpleBean() {
        // Setup
        given(mockFoo.createSettableFutureOfOptionalSimpleBean("key")).willReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Optional<SimpleBean>> result = myClassUnderTest.createSettableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }
}
