package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        def simpleBean = Optional.of(simpleBean1)
        doReturn(simpleBean).when(mockFoo).tryMakeSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        doReturn(Optional.empty()).when(mockFoo).tryMakeSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testTryMakeObservableOfSimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def observable = Observable.just(simpleBean)
        doReturn(observable).when(mockFoo).tryMakeObservableOfSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsNoItem() {
        // Setup
        doReturn(Observable.empty()).when(mockFoo).tryMakeObservableOfSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        def observable = Observable.error(new Exception("message"))
        doReturn(observable).when(mockFoo).tryMakeObservableOfSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanObservable = Observable.just(simpleBean)
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(simpleBeanObservable)

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(Observable.empty())

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        def simpleBeanObservable = Observable.error(new Exception("message"))
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(simpleBeanObservable)

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakePublisherOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanPublisher = Flowable.just(simpleBean)
        when(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).thenReturn(simpleBeanPublisher)

        // Run the test
        def result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakePublisherOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        def simpleBeanPublisher = Flowable.error(new Exception("message"))
        when(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).thenReturn(simpleBeanPublisher)

        // Run the test
        def result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanFlowable = Flowable.just(simpleBean)
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(simpleBeanFlowable)

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(Flowable.empty())

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        def simpleBeanFlowable = Flowable.error(new Exception("message"))
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(simpleBeanFlowable)

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }
}
