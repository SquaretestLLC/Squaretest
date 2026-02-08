package com.myapp;

import com.google.common.base.Optional;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
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
