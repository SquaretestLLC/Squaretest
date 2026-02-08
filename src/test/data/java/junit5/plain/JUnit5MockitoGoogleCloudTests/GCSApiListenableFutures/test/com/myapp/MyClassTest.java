package com.myapp;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.SettableApiFuture;
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
        final ApiFuture<SimpleBean> simpleBeanApiFuture = ApiFutures.immediateFuture(simpleBean);
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanApiFuture);

        // Run the test
        final ApiFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        final ApiFuture<SimpleBean> simpleBeanApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanApiFuture);

        // Run the test
        final ApiFuture<SimpleBean> result = myClassUnderTest.createListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<SimpleBean> result = myClassUnderTest.createSettableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        doReturn(SettableApiFuture.create()).when(mockFoo).createSettableFutureOfBeanSubtype("key");

        // Run the test
        final SettableApiFuture<? extends SimpleBean> result = myClassUnderTest.createSettableFutureOfBeanSubtype(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ApiFuture<List<SimpleBean>> listApiFuture = ApiFutures.immediateFuture(Arrays.asList(simpleBean));
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listApiFuture);

        // Run the test
        final ApiFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final ApiFuture<List<SimpleBean>> listApiFuture = ApiFutures.immediateFuture(Collections.emptyList());
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listApiFuture);

        // Run the test
        final ApiFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        final ApiFuture<List<SimpleBean>> listApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listApiFuture);

        // Run the test
        final ApiFuture<List<SimpleBean>> result = myClassUnderTest.createListenableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<List<SimpleBean>> result = myClassUnderTest.createSettableFutureOfListOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final ApiFuture<ApiFuture<SimpleBean>> apiFutureApiFuture = ApiFutures.immediateFuture(
                ApiFutures.immediateFuture(simpleBean));
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(apiFutureApiFuture);

        // Run the test
        final ApiFuture<ApiFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        final ApiFuture<ApiFuture<SimpleBean>> apiFutureApiFuture = ApiFutures.immediateFailedFuture(
                new Exception("message"));
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(apiFutureApiFuture);

        // Run the test
        final ApiFuture<ApiFuture<SimpleBean>> result = myClassUnderTest.createNestedListenableFutureOfBean("key");

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<SettableApiFuture<SimpleBean>> result = myClassUnderTest.createNestedSettableFutureOfBean(
                "key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(ApiFutures.immediateFuture(new Bar()));

        // Run the test
        final ApiFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ApiFuture<Bar> result = myClassUnderTest.createListenableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<Bar> result = myClassUnderTest.createSettableFutureOfBar("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        doReturn(ApiFutures.immediateFuture("value")).when(mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ApiFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        doReturn(ApiFutures.immediateFailedFuture(new Exception("message"))).when(
                mockFoo).createListenableFutureOfUnknown("key");

        // Run the test
        final ApiFuture<?> result = myClassUnderTest.createListenableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        doReturn(SettableApiFuture.create()).when(mockFoo).createSettableFutureOfUnknown("key");

        // Run the test
        final SettableApiFuture<?> result = myClassUnderTest.createSettableFutureOfUnknown("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<List<?>> result = myClassUnderTest.createSettableFutureOfListOfUnknown("key");

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
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(ApiFutures.immediateFuture(0));

        // Run the test
        final ApiFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ApiFuture<Integer> result = myClassUnderTest.createListenableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<Integer> result = myClassUnderTest.createSettableFutureOfInt("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(ApiFutures.immediateFuture(null));

        // Run the test
        final ApiFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        final ApiFuture result = myClassUnderTest.createListenableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture result = myClassUnderTest.createSettableFutureOfNoType("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ApiFuture<String[]> apiFuture = ApiFutures.immediateFuture(new String[]{"value"});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture);

        // Run the test
        final ApiFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ApiFuture<String[]> apiFuture = ApiFutures.immediateFuture(new String[]{});
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture);

        // Run the test
        final ApiFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        final ApiFuture<String[]> apiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture);

        // Run the test
        final ApiFuture<String[]> result = myClassUnderTest.createListenableFutureOfStringArray("key");

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(SettableApiFuture.create());

        // Run the test
        final SettableApiFuture<String[]> result = myClassUnderTest.createSettableFutureOfStringArray("key");

        // Verify the results
    }
}
