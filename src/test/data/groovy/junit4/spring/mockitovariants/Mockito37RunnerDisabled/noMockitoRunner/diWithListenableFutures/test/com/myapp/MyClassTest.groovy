package com.myapp

import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.SettableFuture
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
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
        def simpleBeanListenableFuture = Futures.immediateFuture(simpleBean)
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBeanListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfBean("key")).thenReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfBean("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        doReturn(SettableFuture.create()).when(mockFoo).createSettableFutureOfBeanSubtype("key")

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
        def listListenableFuture = Futures.immediateFuture([simpleBean])
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(Futures.immediateFuture([]))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        def listListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfListOfBean("key")).thenReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfBean("key")).thenReturn(SettableFuture.create())

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
        def listenableFutureListenableFuture = Futures.immediateFuture(Futures.immediateFuture(simpleBean))
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        def listenableFutureListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createNestedListenableFutureOfBean("key")).thenReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        when(mockFoo.createNestedSettableFutureOfBean("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createNestedSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key")).thenReturn(Futures.immediateFuture(new Bar()))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfBar("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        when(mockFoo.createSettableFutureOfBar("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        doReturn(Futures.immediateFuture("value")).when(mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        doReturn(Futures.immediateFailedFuture(new Exception("message"))).when(mockFoo).createListenableFutureOfUnknown(
                "key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        doReturn(SettableFuture.create()).when(mockFoo).createSettableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        when(mockFoo.createSettableFutureOfListOfUnknown("key")).thenReturn(SettableFuture.create())

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
        when(mockFoo.createListenableFutureOfInt("key")).thenReturn(Futures.immediateFuture(0))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfInt("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        when(mockFoo.createSettableFutureOfInt("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key")).thenReturn(Futures.immediateFuture(null))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        when(mockFoo.createListenableFutureOfNoType("key"))
                .thenReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        when(mockFoo.createSettableFutureOfNoType("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = Futures.immediateFuture(["value"] as String[])
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(Futures.immediateFuture([] as String[]))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        when(mockFoo.createListenableFutureOfStringArray("key")).thenReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        when(mockFoo.createSettableFutureOfStringArray("key")).thenReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfStringArray("key")

        // Verify the results
    }
}
