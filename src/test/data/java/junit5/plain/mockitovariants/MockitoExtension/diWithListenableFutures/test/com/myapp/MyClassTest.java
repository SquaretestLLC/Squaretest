package com.myapp;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
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
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

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
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        doReturn(SettableFuture.create()).when(mockFoo).createSettableFutureOfBeanSubtype("key");

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
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

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
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

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
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(SettableFuture.create());

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
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture);

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
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture);

        // Run the test
        final ListenableFuture<ListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<SettableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(Futures.immediateFuture(new Bar()));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        doReturn(Futures.immediateFuture("value")).when(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        doReturn(Futures.immediateFailedFuture(new Exception("message"))).when(mockFoo).createListenableFutureOfUnknown(
                "key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        doReturn(SettableFuture.create()).when(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListWithWildcard() {
        // Setup
        doReturn(Arrays.asList("value")).when(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        doReturn(Collections.emptyList()).when(mockFoo).createListWithWildcard("key");

        // Run the test
        final List<?> result = myClassUnderTest.createListWithWildcard("key");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCreateMapWithWildcard() {
        // Setup
        final Map<String, ?> expectedResult = new HashMap<>();
        doReturn(new HashMap<>()).when(mockFoo).createMapWithWildcard("key");

        // Run the test
        final Map<String, ?> result = myClassUnderTest.createMapWithWildcard("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreateListenableFutureOfInt() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(Futures.immediateFuture(0));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(Futures.immediateFuture(null));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFuture(new String[]{"value"});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(Futures.immediateFuture(new String[]{}));

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ListenableFuture<String[]> listenableFuture = Futures.immediateFailedFuture(new Exception("message"));
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(SettableFuture.create());

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
        when(mockFoo.createListenableFutureOfOptionalString("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString_FooReturnsNoItem() {
        // Setup
        when(mockFoo.createListenableFutureOfOptionalString("key"))
                .thenReturn(Futures.immediateFuture(Optional.empty()));

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
        when(mockFoo.createListenableFutureOfOptionalString("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalString() {
        // Setup
        when(mockFoo.createSettableFutureOfOptionalString("key")).thenReturn(SettableFuture.create());

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
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean_FooReturnsNoItem() {
        // Setup
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key"))
                .thenReturn(Futures.immediateFuture(Optional.empty()));

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
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalSimpleBean() {
        // Setup
        when(mockFoo.createSettableFutureOfOptionalSimpleBean("key")).thenReturn(SettableFuture.create());

        // Run the test
        final SettableFuture<Optional<SimpleBean>> result = myClassUnderTest.createSettableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }
}
