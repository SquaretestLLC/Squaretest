package com.myapp

import com.google.api.core.ApiFutures
import com.google.api.core.SettableApiFuture
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testMax() {
        assert "result" == MyClass.max(["value"])
    }

    @Test
    void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanApiFuture = ApiFutures.immediateFuture(simpleBean)
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanApiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBeanApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanApiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        doReturn(SettableApiFuture.create()).when(mockFoo).createSettableFutureOfBeanSubtype("key")

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBeanSubtype("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def listApiFuture = ApiFutures.immediateFuture([simpleBean])
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listApiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(ApiFutures.immediateFuture([]))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        def listApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listApiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def apiFutureApiFuture = ApiFutures.immediateFuture(ApiFutures.immediateFuture(simpleBean))
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(apiFutureApiFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        def apiFutureApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(apiFutureApiFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createNestedSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(ApiFutures.immediateFuture(new Bar()))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        doReturn(ApiFutures.immediateFuture("value")).when(mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        doReturn(ApiFutures.immediateFailedFuture(new Exception("message"))).when(
                mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        doReturn(SettableApiFuture.create()).when(mockFoo).createSettableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfListOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListWithWildcard() {
        // Setup
        doReturn(["value"]).when(mockFoo).createListWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createListWithWildcard("key")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        doReturn([]).when(mockFoo).createListWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createListWithWildcard("key")

        // Verify the results
        assert [] == result
    }

    @Test
    void testCreateMapWithWildcard() {
        // Setup
        def expectedResult = ["value": "value"]
        doReturn(["value": "value"]).when(mockFoo).createMapWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createMapWithWildcard("key")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testCreateListenableFutureOfInt() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(ApiFutures.immediateFuture(0))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(ApiFutures.immediateFuture(null))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def apiFuture = ApiFutures.immediateFuture(["value"] as String[])
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def apiFuture = ApiFutures.immediateFuture([] as String[])
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def apiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(apiFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(SettableApiFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfStringArray("key")

        // Verify the results
    }
}
