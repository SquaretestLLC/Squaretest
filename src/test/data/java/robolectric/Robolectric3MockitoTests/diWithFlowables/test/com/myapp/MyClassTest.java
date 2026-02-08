package com.myapp;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.robolectric.RobolectricTestRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        final Optional<? extends SimpleBean> simpleBean = Optional.of(simpleBean1);
        doReturn(simpleBean).when(mockFoo).tryMakeSimpleBean("name");

        // Run the test
        final Optional<? extends SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        doReturn(Optional.empty()).when(mockFoo).tryMakeSimpleBean("name");

        // Run the test
        final Optional<? extends SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testTryMakeObservableOfSimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Observable<? extends SimpleBean> observable = Observable.just(simpleBean);
        doReturn(observable).when(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeObservableOfSimpleBean_FooReturnsNoItem() {
        // Setup
        doReturn(Observable.empty()).when(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeObservableOfSimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfSimpleBean(...).
        final Observable<? extends SimpleBean> observable = Observable.error(new Exception("message"));
        doReturn(observable).when(mockFoo).tryMakeObservableOfSimpleBean("name");

        // Run the test
        final Observable<? extends SimpleBean> result = myClassUnderTest.tryMakeObservableOfSimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeObservableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Observable<SimpleBean> simpleBeanObservable = Observable.just(simpleBean);
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(simpleBeanObservable);

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeObservableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(Observable.empty());

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeObservableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeObservableOfOnlySimpleBean(...).
        final Observable<SimpleBean> simpleBeanObservable = Observable.error(new Exception("message"));
        when(mockFoo.tryMakeObservableOfOnlySimpleBean("name")).thenReturn(simpleBeanObservable);

        // Run the test
        final Observable<SimpleBean> result = myClassUnderTest.tryMakeObservableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakePublisherOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Publisher<SimpleBean> simpleBeanPublisher = Flowable.just(simpleBean);
        when(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).thenReturn(simpleBeanPublisher);

        // Run the test
        final Publisher<SimpleBean> result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakePublisherOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakePublisherOfOnlySimpleBean(...).
        final Publisher<SimpleBean> simpleBeanPublisher = Flowable.error(new Exception("message"));
        when(mockFoo.tryMakePublisherOfOnlySimpleBean("name")).thenReturn(simpleBeanPublisher);

        // Run the test
        final Publisher<SimpleBean> result = myClassUnderTest.tryMakePublisherOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeFlowableOfOnlySimpleBean() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Flowable<SimpleBean> simpleBeanFlowable = Flowable.just(simpleBean);
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(simpleBeanFlowable);

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeFlowableOfOnlySimpleBean_FooReturnsNoItem() {
        // Setup
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(Flowable.empty());

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeFlowableOfOnlySimpleBean_FooReturnsError() {
        // Setup
        // Configure Foo.tryMakeFlowableOfOnlySimpleBean(...).
        final Flowable<SimpleBean> simpleBeanFlowable = Flowable.error(new Exception("message"));
        when(mockFoo.tryMakeFlowableOfOnlySimpleBean("name")).thenReturn(simpleBeanFlowable);

        // Run the test
        final Flowable<SimpleBean> result = myClassUnderTest.tryMakeFlowableOfOnlySimpleBean("name");

        // Verify the results
    }
}
