package com.myapp

import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.SettableFuture
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.willReturn

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testMax() {
        assertThat(MyClass.max(["value"])).isEqualTo("result")
    }

    @Test
    void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanListenableFuture = Futures.immediateFuture(simpleBean)
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBeanListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfBean("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfBeanSubtype("key")

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
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsNoItems() {
        // Setup
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(Futures.immediateFuture([]))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        def listListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfBean("key")).willReturn(SettableFuture.create())

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
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        def listenableFutureListenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        given(mockFoo.createNestedSettableFutureOfBean("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createNestedSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key")).willReturn(Futures.immediateFuture(new Bar()))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        given(mockFoo.createSettableFutureOfBar("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        willReturn(Futures.immediateFuture("value")).given(mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        willReturn(Futures.immediateFailedFuture(new Exception("message"))).given(
                mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        willReturn(SettableFuture.create()).given(mockFoo).createSettableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfUnknown("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfListOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListWithWildcard() {
        // Setup
        willReturn(["value"]).given(mockFoo).createListWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createListWithWildcard("key")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testCreateListWithWildcard_FooReturnsNoItems() {
        // Setup
        willReturn([]).given(mockFoo).createListWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createListWithWildcard("key")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testCreateMapWithWildcard() {
        // Setup
        def expectedResult = ["value": "value"]
        willReturn(["value": "value"]).given(mockFoo).createMapWithWildcard("key")

        // Run the test
        def result = myClassUnderTest.createMapWithWildcard("key")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testCreateListenableFutureOfInt() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key")).willReturn(Futures.immediateFuture(0))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        given(mockFoo.createSettableFutureOfInt("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key")).willReturn(Futures.immediateFuture(null))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key"))
                .willReturn(Futures.immediateFailedFuture(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        given(mockFoo.createSettableFutureOfNoType("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = Futures.immediateFuture(["value"] as String[])
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsNoItems() {
        // Setup
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(Futures.immediateFuture([] as String[]))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = Futures.immediateFailedFuture(new Exception("message"))
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        given(mockFoo.createSettableFutureOfStringArray("key")).willReturn(SettableFuture.create())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfStringArray("key")

        // Verify the results
    }
}
