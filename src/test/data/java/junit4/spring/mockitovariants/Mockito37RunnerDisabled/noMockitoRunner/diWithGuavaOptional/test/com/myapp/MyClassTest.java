package com.myapp;

import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final Optional<SimpleBean> simpleBeanOptional = Optional.of(simpleBean);
        when(mockFoo.tryMakeSimpleBean("name")).thenReturn(simpleBeanOptional);

        // Run the test
        final Optional<SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
    }

    @Test
    public void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        when(mockFoo.tryMakeSimpleBean("name")).thenReturn(Optional.absent());

        // Run the test
        final Optional<SimpleBean> result = myClassUnderTest.tryMakeSimpleBean("name");

        // Verify the results
        assertEquals(Optional.absent(), result);
    }
}
