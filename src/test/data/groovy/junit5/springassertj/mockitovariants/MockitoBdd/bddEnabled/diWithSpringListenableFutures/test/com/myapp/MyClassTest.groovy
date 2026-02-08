package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.util.concurrent.SettableListenableFuture

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.willReturn
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
        assertThat(MyClass.max(["value"])).isEqualTo("result")
    }

    @Test
    void testCreateListenableFutureOfBean() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanListenableFuture = AsyncResult.forValue(simpleBean)
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfBean(...).
        def simpleBeanListenableFuture = AsyncResult.forExecutionException(new Exception("message"))
        given(mockFoo.createListenableFutureOfBean("key")).willReturn(simpleBeanListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfBean("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBeanSubtype() {
        // Setup
        willReturn(new SettableListenableFuture<>()).given(mockFoo).createSettableFutureOfBeanSubtype("key")

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
        def listListenableFuture = AsyncResult.forValue([simpleBean])
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfListOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfListOfBean(...).
        def listListenableFuture = AsyncResult.forExecutionException(new Exception("message"))
        given(mockFoo.createListenableFutureOfListOfBean("key")).willReturn(listListenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfListOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfBean() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfBean("key")).willReturn(new SettableListenableFuture<>())

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
        def listenableFutureListenableFuture = AsyncResult.forValue(AsyncResult.forValue(simpleBean))
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedListenableFutureOfBean_FooReturnsFailure() {
        // Setup
        // Configure Foo.createNestedListenableFutureOfBean(...).
        def listenableFutureListenableFuture = AsyncResult.forExecutionException(new Exception("message"))
        given(mockFoo.createNestedListenableFutureOfBean("key")).willReturn(listenableFutureListenableFuture)

        // Run the test
        def result = myClassUnderTest.createNestedListenableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateNestedSettableFutureOfBean() {
        // Setup
        given(mockFoo.createNestedSettableFutureOfBean("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createNestedSettableFutureOfBean("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key")).willReturn(AsyncResult.forValue(new Bar()))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfBar_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfBar("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfBar() {
        // Setup
        given(mockFoo.createSettableFutureOfBar("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfBar("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown() {
        // Setup
        willReturn(AsyncResult.forValue("value")).given(mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfUnknown_FooReturnsFailure() {
        // Setup
        willReturn(AsyncResult.forExecutionException(new Exception("message"))).given(
                mockFoo).createListenableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfUnknown() {
        // Setup
        willReturn(new SettableListenableFuture<>()).given(mockFoo).createSettableFutureOfUnknown("key")

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfUnknown("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfListOfUnknown() {
        // Setup
        given(mockFoo.createSettableFutureOfListOfUnknown("key")).willReturn(new SettableListenableFuture<>())

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
        given(mockFoo.createListenableFutureOfInt("key")).willReturn(AsyncResult.forValue(0))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfInt_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfInt("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfInt() {
        // Setup
        given(mockFoo.createSettableFutureOfInt("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfInt("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key")).willReturn(AsyncResult.forValue(null))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfNoType_FooReturnsFailure() {
        // Setup
        given(mockFoo.createListenableFutureOfNoType("key"))
                .willReturn(AsyncResult.forExecutionException(new Exception("message")))

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfNoType() {
        // Setup
        given(mockFoo.createSettableFutureOfNoType("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfNoType("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = AsyncResult.forValue(["value"] as String[])
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateListenableFutureOfStringArray_FooReturnsFailure() {
        // Setup
        // Configure Foo.createListenableFutureOfStringArray(...).
        def listenableFuture = AsyncResult.forExecutionException(new Exception("message"))
        given(mockFoo.createListenableFutureOfStringArray("key")).willReturn(listenableFuture)

        // Run the test
        def result = myClassUnderTest.createListenableFutureOfStringArray("key")

        // Verify the results
    }

    @Test
    void testCreateSettableFutureOfStringArray() {
        // Setup
        given(mockFoo.createSettableFutureOfStringArray("key")).willReturn(new SettableListenableFuture<>())

        // Run the test
        def result = myClassUnderTest.createSettableFutureOfStringArray("key")

        // Verify the results
    }
}
