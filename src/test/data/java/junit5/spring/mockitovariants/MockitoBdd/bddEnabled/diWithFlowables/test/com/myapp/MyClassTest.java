package com.myapp;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.reactivestreams.Publisher;

import java.util.Optional;

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
    void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        final Optional<? extends SimpleBean> simpleBean = Optional.of(simpleBean1);
        willReturn(simpleBean).given(mockFoo).tryMakeSimpleBean("name");

        // Run the test
        final Optional<? extends SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        willReturn(Optional.empty()).given(mockFoo).tryMakeSimpleBean("name");

        // Run the test
        final Optional<? extends SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testTryMakeObservableOfSimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Observable<? extends SimpleBean> observable = Observable.just(simpleBean);
        willReturn(observable).given(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsNoItem() {
        // Setup
        willReturn(Observable.empty()).given(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfSimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        final Observable<? extends SimpleBean> observable = Observable.error(new Exception("message"));
        willReturn(observable).given(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Observable<SimpleBean> simpleBeanObservable = Observable.just(simpleBean);
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(simpleBeanObservable);

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(Observable.empty());

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeObservableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        final Observable<SimpleBean> simpleBeanObservable = Observable.error(new Exception("message"));
        given(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).willReturn(simpleBeanObservable);

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakePublisherOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Publisher<SimpleBean> simpleBeanPublisher = Flowable.just(simpleBean);
        given(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).willReturn(simpleBeanPublisher);

        // Run the test
        final Publisher<SimpleBean> result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakePublisherOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        final Publisher<SimpleBean> simpleBeanPublisher = Flowable.error(new Exception("message"));
        given(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).willReturn(simpleBeanPublisher);

        // Run the test
        final Publisher<SimpleBean> result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Flowable<SimpleBean> simpleBeanFlowable = Flowable.just(simpleBean);
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(simpleBeanFlowable);

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(Flowable.empty());

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    void testTryMakeFlowableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        final Flowable<SimpleBean> simpleBeanFlowable = Flowable.error(new Exception("message"));
        given(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).willReturn(simpleBeanFlowable);

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }
}
