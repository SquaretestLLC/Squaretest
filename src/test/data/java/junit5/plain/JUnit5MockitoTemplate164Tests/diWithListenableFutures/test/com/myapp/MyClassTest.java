package com.myapp;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
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
    void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final SettableFuture<SimpleBean> simpleBeanListenableFuture = SettableFuture.create();
        simpleBeanListenableFuture.set(simpleBean);
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final SettableFuture<SimpleBean> simpleBeanListenableFuture = SettableFuture.create();
        simpleBeanListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture);

        // Run the test
        final ListenableFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        // Configure Foo.createSettableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final SettableFuture<SimpleBean> simpleBeanSettableFuture = SettableFuture.create();
        simpleBeanSettableFuture.set(simpleBean);
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(simpleBeanSettableFuture);

        // Run the test
        final SettableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfBean(...).
        final SettableFuture<SimpleBean> simpleBeanSettableFuture = SettableFuture.create();
        simpleBeanSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(simpleBeanSettableFuture);

        // Run the test
        final SettableFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        // Configure Foo.createSettableFutureOfBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final SettableFuture<SimpleBean> settableFuture = SettableFuture.create();
        settableFuture.set(simpleBean);
        doReturn(settableFuture).when(mockFoo).createSettableFutureOfBeanSubtype("key");

        // Run the test
        final SettableFuture<? extends SimpleBean> result = myClassUnderTest.createSettableFutureOfBeanSubtype("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfBeanSubtype(...).
        final SettableFuture<SimpleBean> settableFuture = SettableFuture.create();
        settableFuture.setException(new Exception("message"));
        doReturn(settableFuture).when(mockFoo).createSettableFutureOfBeanSubtype("key");

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
        final List<SimpleBean> simpleBeans = Arrays.asList(simpleBean);
        final SettableFuture<List<SimpleBean>> listListenableFuture = SettableFuture.create();
        listListenableFuture.set(simpleBeans);
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final SettableFuture<List<SimpleBean>> listListenableFuture = SettableFuture.create();
        listListenableFuture.set(Collections.emptyList());
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final SettableFuture<List<SimpleBean>> listListenableFuture = SettableFuture.create();
        listListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture);

        // Run the test
        final ListenableFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final List<SimpleBean> simpleBeans = Arrays.asList(simpleBean);
        final SettableFuture<List<SimpleBean>> listSettableFuture = SettableFuture.create();
        listSettableFuture.set(simpleBeans);
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(listSettableFuture);

        // Run the test
        final SettableFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfBean(...).
        final SettableFuture<List<SimpleBean>> listSettableFuture = SettableFuture.create();
        listSettableFuture.set(Collections.emptyList());
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(listSettableFuture);

        // Run the test
        final SettableFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfBean(...).
        final SettableFuture<List<SimpleBean>> listSettableFuture = SettableFuture.create();
        listSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(listSettableFuture);

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
        final SettableFuture<SimpleBean> simpleBeanListenableFuture = SettableFuture.create();
        simpleBeanListenableFuture.set(simpleBean);
        final SettableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = SettableFuture.create();
        listenableFutureListenableFuture.set(simpleBeanListenableFuture);
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
        final SettableFuture<ListenableFuture<SimpleBean>> listenableFutureListenableFuture = SettableFuture.create();
        listenableFutureListenableFuture.setException(new Exception("message"));
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture);

        // Run the test
        final ListenableFuture<ListenableFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        // Configure Foo.createNestedSettableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final SettableFuture<SimpleBean> simpleBeanSettableFuture = SettableFuture.create();
        simpleBeanSettableFuture.set(simpleBean);
        final SettableFuture<SettableFuture<SimpleBean>> settableFutureSettableFuture = SettableFuture.create();
        settableFutureSettableFuture.set(simpleBeanSettableFuture);
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(settableFutureSettableFuture);

        // Run the test
        final SettableFuture<SettableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedSettableFutureOfBean(...).
        final SettableFuture<SettableFuture<SimpleBean>> settableFutureSettableFuture = SettableFuture.create();
        settableFutureSettableFuture.setException(new Exception("message"));
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(settableFutureSettableFuture);

        // Run the test
        final SettableFuture<SettableFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        // Configure Foo.createListenableFutureOfBar(...).
        final Bar bar = new Bar();
        final SettableFuture<Bar> barListenableFuture = SettableFuture.create();
        barListenableFuture.set(bar);
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(barListenableFuture);

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBar(...).
        final SettableFuture<Bar> barListenableFuture = SettableFuture.create();
        barListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(barListenableFuture);

        // Run the test
        final ListenableFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        // Configure Foo.createSettableFutureOfBar(...).
        final Bar bar = new Bar();
        final SettableFuture<Bar> barSettableFuture = SettableFuture.create();
        barSettableFuture.set(bar);
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(barSettableFuture);

        // Run the test
        final SettableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfBar(...).
        final SettableFuture<Bar> barSettableFuture = SettableFuture.create();
        barSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(barSettableFuture);

        // Run the test
        final SettableFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        // Configure Foo.createListenableFutureOfUnknown(...).
        final SettableFuture<Object> listenableFuture = SettableFuture.create();
        listenableFuture.set("value");
        doReturn(listenableFuture).when(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfUnknown(...).
        final SettableFuture<Object> listenableFuture = SettableFuture.create();
        listenableFuture.setException(new Exception("message"));
        doReturn(listenableFuture).when(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ListenableFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        // Configure Foo.createSettableFutureOfUnknown(...).
        final SettableFuture<Object> settableFuture = SettableFuture.create();
        settableFuture.set("value");
        doReturn(settableFuture).when(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfUnknown(...).
        final SettableFuture<Object> settableFuture = SettableFuture.create();
        settableFuture.setException(new Exception("message"));
        doReturn(settableFuture).when(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfUnknown(...).
        final SettableFuture<List<?>> listSettableFuture = SettableFuture.create();
        listSettableFuture.set(Arrays.asList("value"));
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(listSettableFuture);

        // Run the test
        final SettableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfUnknown(...).
        final SettableFuture<List<?>> listSettableFuture = SettableFuture.create();
        listSettableFuture.set(Collections.emptyList());
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(listSettableFuture);

        // Run the test
        final SettableFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfListOfUnknown(...).
        final SettableFuture<List<?>> listSettableFuture = SettableFuture.create();
        listSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(listSettableFuture);

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
        // Configure Foo.createListenableFutureOfInt(...).
        final SettableFuture<Integer> integerListenableFuture = SettableFuture.create();
        integerListenableFuture.set(0);
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(integerListenableFuture);

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfInt(...).
        final SettableFuture<Integer> integerListenableFuture = SettableFuture.create();
        integerListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(integerListenableFuture);

        // Run the test
        final ListenableFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        // Configure Foo.createSettableFutureOfInt(...).
        final SettableFuture<Integer> integerSettableFuture = SettableFuture.create();
        integerSettableFuture.set(0);
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(integerSettableFuture);

        // Run the test
        final SettableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfInt(...).
        final SettableFuture<Integer> integerSettableFuture = SettableFuture.create();
        integerSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(integerSettableFuture);

        // Run the test
        final SettableFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        // Configure Foo.createListenableFutureOfNoType(...).
        final V v = null;
        final SettableFuture<V> listenableFuture = SettableFuture.create();
        listenableFuture.set(v);
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfNoType(...).
        final SettableFuture<V> listenableFuture = SettableFuture.create();
        listenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        // Configure Foo.createSettableFutureOfNoType(...).
        final V v = null;
        final SettableFuture<V> settableFuture = SettableFuture.create();
        settableFuture.set(v);
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(settableFuture);

        // Run the test
        final SettableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfNoType(...).
        final SettableFuture<V> settableFuture = SettableFuture.create();
        settableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(settableFuture);

        // Run the test
        final SettableFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final SettableFuture<String[]> listenableFuture = SettableFuture.create();
        listenableFuture.set(new String[]{"value"});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final SettableFuture<String[]> listenableFuture = SettableFuture.create();
        listenableFuture.set(new String[]{});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final SettableFuture<String[]> listenableFuture = SettableFuture.create();
        listenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture);

        // Run the test
        final ListenableFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        // Configure Foo.createSettableFutureOfStringArray(...).
        final SettableFuture<String[]> settableFuture = SettableFuture.create();
        settableFuture.set(new String[]{"value"});
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(settableFuture);

        // Run the test
        final SettableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createSettableFutureOfStringArray(...).
        final SettableFuture<String[]> settableFuture = SettableFuture.create();
        settableFuture.set(new String[]{});
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(settableFuture);

        // Run the test
        final SettableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfStringArray(...).
        final SettableFuture<String[]> settableFuture = SettableFuture.create();
        settableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(settableFuture);

        // Run the test
        final SettableFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.set(Optional.of("value"));
        when(mockFoo.createListenableFutureOfOptionalString("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.set(Optional.empty());
        when(mockFoo.createListenableFutureOfOptionalString("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalString_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfOptionalString("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<String>> result = myClassUnderTest.createListenableFutureOfOptionalString(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalString() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.set(Optional.of("value"));
        when(mockFoo.createSettableFutureOfOptionalString("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<String>> result = myClassUnderTest.createSettableFutureOfOptionalString("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalString_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.set(Optional.empty());
        when(mockFoo.createSettableFutureOfOptionalString("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<String>> result = myClassUnderTest.createSettableFutureOfOptionalString("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalString_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalString(...).
        final SettableFuture<Optional<String>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfOptionalString("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<String>> result = myClassUnderTest.createSettableFutureOfOptionalString("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalSimpleBean(...).
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        final Optional<SimpleBean> simpleBean = Optional.of(simpleBean1);
        final SettableFuture<Optional<SimpleBean>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.set(simpleBean);
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalSimpleBean(...).
        final SettableFuture<Optional<SimpleBean>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.set(Optional.empty());
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfOptionalSimpleBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfOptionalSimpleBean(...).
        final SettableFuture<Optional<SimpleBean>> optionalListenableFuture = SettableFuture.create();
        optionalListenableFuture.setException(new Exception("message"));
        when(mockFoo.createListenableFutureOfOptionalSimpleBean("key")).thenReturn(optionalListenableFuture);

        // Run the test
        final ListenableFuture<Optional<SimpleBean>> result = myClassUnderTest.createListenableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalSimpleBean() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalSimpleBean(...).
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        final Optional<SimpleBean> simpleBean = Optional.of(simpleBean1);
        final SettableFuture<Optional<SimpleBean>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.set(simpleBean);
        when(mockFoo.createSettableFutureOfOptionalSimpleBean("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<SimpleBean>> result = myClassUnderTest.createSettableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalSimpleBean_FooReturnsNoItem() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalSimpleBean(...).
        final SettableFuture<Optional<SimpleBean>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.set(Optional.empty());
        when(mockFoo.createSettableFutureOfOptionalSimpleBean("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<SimpleBean>> result = myClassUnderTest.createSettableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfOptionalSimpleBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createSettableFutureOfOptionalSimpleBean(...).
        final SettableFuture<Optional<SimpleBean>> optionalSettableFuture = SettableFuture.create();
        optionalSettableFuture.setException(new Exception("message"));
        when(mockFoo.createSettableFutureOfOptionalSimpleBean("key")).thenReturn(optionalSettableFuture);

        // Run the test
        final SettableFuture<Optional<SimpleBean>> result = myClassUnderTest.createSettableFutureOfOptionalSimpleBean(
                "key");

        // Verify the results
    }

    @Test
    void testMax() {
        assertEquals("result", MyClass.max(Arrays.asList("value")));
    }
}
