package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

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
    void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        def simpleBean = Optional.of(simpleBean1)
        willReturn(simpleBean).given(mockFoo).tryMakeSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        willReturn(Optional.empty()).given(mockFoo).tryMakeSimpleBean("name")

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
        willReturn(observable).given(mockFoo).tryMakeObservableOfSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsNoItem() {
        // Setup
        willReturn(Observable.empty()).given(mockFoo).tryMakeObservableOfSimpleBean("name")

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        def observable = Observable.error(new Exception("message"))
        willReturn(observable).given(mockFoo).tryMakeObservableOfSimpleBean("name")

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
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(simpleBeanObservable)

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(Observable.empty())

        // Run the test
        def result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        def simpleBeanObservable = Observable.error(new Exception("message"))
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(simpleBeanObservable)

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
        given(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).willReturn(simpleBeanPublisher)

        // Run the test
        def result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakePublisherOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        def simpleBeanPublisher = Flowable.error(new Exception("message"))
        given(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).willReturn(simpleBeanPublisher)

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
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(simpleBeanFlowable)

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(Flowable.empty())

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        def simpleBeanFlowable = Flowable.error(new Exception("message"))
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(simpleBeanFlowable)

        // Run the test
        def result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name")

        // Verify the results
    }
}
